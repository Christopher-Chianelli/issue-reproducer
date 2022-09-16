import jpype
import jpype.imports

jpype.startJVM(classpath=['target/issue-reproducer-8.11.0.Final.jar'], convertStrings=True)

from org.acme import MyClass

# Passing a string with NULL bytes to Java works
assert MyClass.getLength('\u0000') == 1

# Reading a returned string with NULL bytes from Java fails
out = MyClass.getArgument('String\u0000with\u0000NULL\u0000Bytes')
print(out)

assert out == 'String\u0000with\u0000NULL\u0000Bytes'

