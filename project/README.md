# EntityScanner.scan fails to return annotated classes in native mode

This is a demo of the root cause of why we need to do AOT generated classes.
ClassLoadingPostProcessor scan for classes with specific annotations and use
it to register beans.
Outside of native mode, this works fine.
In native mode, an empty list is returned, even though all the classes are
registered for reflection.

Because of this, we need to create an AOT processor that prebuilds the
result (and thus does no classpath scanning) (not shown here for brevity).

Run with

```bash
mvn -PnativeTest test
```

Note non-native tests pass, but native tests fail

