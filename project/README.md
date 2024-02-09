# Reachability Metadata for org.glassfish.jaxb:jaxb-runtime conditions are not met even though they are used

In the native image, the test fails with:
```
  JUnit Jupiter:MainTest:testToXml()
    MethodSource [className = 'org.acme.MainTest', methodName = 'testToXml', methodParameterTypes = '']
    => org.graalvm.nativeimage.MissingReflectionRegistrationError: The program tried to reflectively invoke method public org.glassfish.jaxb.runtime.v2.runtime.property.SingleElementNodeProperty(org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl,org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo) without it being registered for runtime reflection. Add public org.glassfish.jaxb.runtime.v2.runtime.property.SingleElementNodeProperty(org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl,org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo) to the reflection metadata to solve this problem. See https://www.graalvm.org/latest/reference-manual/native-image/metadata/#reflection for help.
       org.graalvm.nativeimage.builder/com.oracle.svm.core.reflect.MissingReflectionRegistrationUtils.forQueriedOnlyExecutable(MissingReflectionRegistrationUtils.java:72)
       java.base@21.0.2/java.lang.reflect.Constructor.acquireConstructorAccessor(Constructor.java:74)
       java.base@21.0.2/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499)
       java.base@21.0.2/java.lang.reflect.Constructor.newInstance(Constructor.java:486)
       org.glassfish.jaxb.runtime.v2.runtime.property.PropertyFactory.create(PropertyFactory.java:94)
       org.glassfish.jaxb.runtime.v2.runtime.ClassBeanInfoImpl.<init>(ClassBeanInfoImpl.java:148)
       org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl.getOrCreate(JAXBContextImpl.java:464)
       org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl.<init>(JAXBContextImpl.java:283)
       org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl$JAXBContextBuilder.build(JAXBContextImpl.java:1115)
       org.glassfish.jaxb.runtime.v2.ContextFactory.createContext(ContextFactory.java:144)
       [...]
```

Run with
```bash
mvn -Pnative clean test
```

Notes:

- Since release 0.9.2 of the native-maven-plugin, [the reachability metadata repository is enabled by default](https://graalvm.github.io/native-build-tools/latest/maven-plugin.html#metadata-support).

- In the logs, the reachability metadata for jaxb-runtime is added:
```
[INFO] [graalvm reachability metadata repository for org.glassfish.jaxb:jaxb-runtime:4.0.4]: Configuration directory not found. Trying latest version.
[INFO] [graalvm reachability metadata repository for org.glassfish.jaxb:jaxb-runtime:4.0.4]: Configuration directory is org.glassfish.jaxb/jaxb-runtime/3.0.2
```

- When run with the agent
```bash
mvn -Pnative -Dagent=true clean test
```
the tests pass

- Minimum working reflect-config.json
```json
[
  {
    "name":"org.acme.MyAnnotatedClass",
    "allDeclaredFields":true,
    "queryAllDeclaredMethods":true,
    "methods":[{"name":"<init>","parameterTypes":[] }, {"name":"getType","parameterTypes":[] }]
  },
  {
    "name":"org.glassfish.jaxb.runtime.v2.runtime.property.SingleElementNodeProperty",
    "queryAllPublicConstructors":true,
    "methods":[{"name":"<init>","parameterTypes":["org.glassfish.jaxb.runtime.v2.runtime.JAXBContextImpl","org.glassfish.jaxb.runtime.v2.model.runtime.RuntimeElementPropertyInfo"] }]
  }
]
```
