# Registering java.lang.Class using ReflectionHints causes ObjectMapper to not be usable inside AOT code

This is a demo that demostrates the need to have a good way to
recursively register a type and the types of all its fields for
reflection. In ClassLoadingAotContribution, there is a hard
to spot issue in registerForReflection: it will register
java.lang.Class for reflection, since MyAnnotatedClass has
a Class<?> field. This causes GraalVM to think the
unsupported field java.lang.ClassValue.hashCodeForCache is reachable,
even though the ObjectMapper will not access it. To fix the issue,
registerForReflection must not register java.lang.Class or
java.lang.ClassLoader. You can verify this is indeed the cause
by making `BANNED_CLASSES = Set.of(Class.class)` instead of
`BANNED_CLASSES = Set.of();`, which will cause the native
tests to pass.

Run with

```bash
mvn -PnativeTest test
```

Note non-native tests pass, but native tests fail

