# JAXB reachability metadata is not available

A demo to show using JAXB with the default configuration
causes a Runtime failure due to missing metadata in
native mode. The failure occurs in MyController.

Run with

```bash
mvn -PnativeTest test
```

Note non-native tests pass, but native tests fail

