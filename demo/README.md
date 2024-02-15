## BeanDefinition ConstructorArgumentValues do not support non-common value types in native mode

A bean definition that uses non-common value types like this:

```java
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(MyBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new MyBeanRecord("Bob", 12));
        registry.registerBeanDefinition("MyBean", beanDefinition);
    }
```
will cause a `ValueCodeGeneration` exception.
Since this use case is intentionally unsupported, a helpful error message saying "`ConstructorArgumentValues` do not support code generation for arguments of type (MyBeanRecord)" should be thrown instead, similar to [what happens with instance suppliers](https://github.com/spring-projects/spring-framework/issues/29556).
When run without AOT, i.e.

```shell
mvn test
```

The tests will pass. When run with AOT, i.e.:

```shell
mvn -PnativeTest test
```

The tests will fail with the following exception:
```text
TestContextAotException: Failed to generate AOT artifacts for test classes [com.example.demo.DemoApplicationTests]
...
Caused by: TestContextAotException: Failed to process test class [com.example.demo.DemoApplicationTests] for AOT
...
Caused by: ValueCodeGenerationException: Failed to generate code for 'MyBeanRecord[name=Bob, age=12]' with type class com.example.demo.MyBeanRecord
...
Caused by: UnsupportedTypeValueCodeGenerationException: Code generation does not support com.example.demo.MyBeanRecord
```
