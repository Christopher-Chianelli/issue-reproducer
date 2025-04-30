import inspect
import jpype
import jpype.imports
from jpype import JImplements, JOverride, JObject
from time import sleep

jpype.startJVM(classpath=['target/issue-reproducer-999-SNAPSHOT.jar'])

from java.lang import Deprecated

from org.acme import MyClass

obj = MyClass()
print(obj.javaResult())
print(f'Python: {obj}')
