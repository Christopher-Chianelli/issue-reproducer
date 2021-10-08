from collections.abc import Sequence

import jpype
import jpype.imports
from jpype import _jcustomizer
from jpype import JClass


# Example showing JConversion works with java.lang.Collection
class MySequence:
    def __init__(self, items):
        self.items = items

    def __getitem__(self, index):
        return self.items[index]

    def __len__(self):
        return len(self.items)


@_jcustomizer.JConversion("java.util.Collection", instanceof=MySequence)
def _MySequenceConvert(jcls, obj):
    return JClass('java.util.Arrays').asList(obj)


# Example showing JConversion does not work with java.lang.Class
class ProxyClassInstance:
    def __init__(self, proxy):
        self.proxy = proxy


@_jcustomizer.JConversion("java.lang.Class", instanceof=ProxyClassInstance)
def _ProxyClassConvert(jcls, obj):
    return obj.proxy


jpype.startJVM(classpath=['target/issue-reproducer-8.11.0.Final.jar'])

from org.acme import MyClass

out = MyClass.joinCollection(['a', 'b', 'c'])
assert out == 'a, b, c'

out = MyClass.joinCollection(MySequence(['a', 'b', 'c']))
assert out == 'a, b, c'

out = MyClass.getClassSimpleName(JClass('java.lang.String'))
assert out == 'String'

out = MyClass.getClassSimpleName(ProxyClassInstance(JClass('java.lang.String')))
assert out == 'String'
