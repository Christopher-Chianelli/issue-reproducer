import inspect
import jpype
import jpype.imports
from jpype import JImplements, JOverride, JObject

jpype.startJVM(classpath=['target/issue-reproducer-8.11.0.Final.jar'])

from java.util.function import Function, BiFunction

# Workaround classes


@JImplements(Function)
class ProxyFunction:
    def __init__(self, proxy):
        self.proxy = proxy

    @JOverride
    def apply(self, arg):
        return self.proxy(arg)


@JImplements(BiFunction)
class ProxyBiFunction:
    def __init__(self, proxy):
        self.proxy = proxy

    @JOverride
    def apply(self, arg1, arg2):
        return self.proxy(arg1, arg2)


# Workaround method
def cast(python_function):
    parameters = inspect.signature(python_function).parameters
    arg_count = len(parameters)
    if arg_count == 1:
        return JObject(python_function, Function)
    elif arg_count == 2:
        return JObject(python_function, BiFunction)
    else:
        raise Exception(f'Unsupported number of arguments: {arg_count}')


def single_arg_function(arg):
    return f'Number {arg}'


def two_arg_function(arg1, arg2):
    return f'Number {arg1} and {arg2}'


from org.acme import MyClass

assert str(MyClass.noOverloadApply(single_arg_function)) == 'Number 1'

# Workaround
assert str(MyClass.apply(cast(single_arg_function))) == 'Number 1'
assert str(MyClass.apply(cast(two_arg_function))) == 'Number 1 and 2'

# Ambiguous issue
assert str(MyClass.apply(single_arg_function)) == 'Number 1'
assert str(MyClass.apply(two_arg_function)) == 'Number 1 and 2'
