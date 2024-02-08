package org.acme;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.aot.generate.GeneratedMethod;
import org.springframework.aot.generate.GenerationContext;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationCode;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.javapoet.CodeBlock;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClassLoadingAotContribution implements BeanFactoryInitializationAotContribution {
    final Set<Class<?>> classSet;
    final static Set<Class<?>> BANNED_CLASSES = Set.of();
    // Uncomment to see that if java.lang.Class is never registered for reflection,
    // ObjectMapper works
    // final static Set<Class<?>> BANNED_CLASSES = Set.of(Class.class);

    public ClassLoadingAotContribution(Set<Class<?>> classSet) {
        this.classSet = classSet;
    }

    public static void registerBeans(ConfigurableListableBeanFactory beanFactory, List<Object> beans) {
        for (Object bean : beans) {
            beanFactory.registerSingleton(bean.getClass().getSimpleName(), bean);
        }
    }

    private static void registerForReflection(ReflectionHints reflectionHints, Class<?> type, Set<Class<?>> visited) {
        if (type == null || BANNED_CLASSES.contains(type) || visited.contains(type)) {
            return;
        }
        visited.add(type);
        reflectionHints.registerType(type,
                MemberCategory.INTROSPECT_PUBLIC_METHODS,
                MemberCategory.INTROSPECT_DECLARED_METHODS,
                MemberCategory.INTROSPECT_DECLARED_CONSTRUCTORS,
                MemberCategory.INTROSPECT_PUBLIC_CONSTRUCTORS,
                MemberCategory.PUBLIC_FIELDS,
                MemberCategory.DECLARED_FIELDS,
                MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,
                MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS,
                MemberCategory.INVOKE_DECLARED_METHODS,
                MemberCategory.INVOKE_PUBLIC_METHODS);
        for (Field field : type.getDeclaredFields()) {
            registerForReflection(reflectionHints, field.getType(), visited);
        }
        registerForReflection(reflectionHints, type.getSuperclass(), visited);
    }

    @Override
    public void applyTo(GenerationContext generationContext, BeanFactoryInitializationCode beanFactoryInitializationCode) {
        ReflectionHints reflectionHints = generationContext.getRuntimeHints().reflection();
        registerForReflection(reflectionHints, MyAnnotatedClass.class, new HashSet<>());
        GeneratedMethod generatedMethod = beanFactoryInitializationCode.getMethods().add("registerBeans", builder -> {
            builder.addParameter(ConfigurableListableBeanFactory.class, "beanFactory");
            var code = CodeBlock.builder();
            code.beginControlFlow("try");
            code.add("$T<Object> beans = new $T($L);\n", List.class, ArrayList.class, classSet.size());
            code.add("$T objectMapper = new $T();\n", ObjectMapper.class, ObjectMapper.class);
            for (Class<?> type : classSet) {
                try {
                    MyAnnotatedClass instance = (MyAnnotatedClass) type.getConstructor().newInstance();
                    instance.setType(instance.getClass());
                    StringWriter xml = new StringWriter();
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writeValue(xml, instance);

                    code.add("beans.add(objectMapper.readerFor($T.class).readValue($S));\n",
                            instance.getClass(),
                            xml.toString());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
            code.add("$T.registerBeans(beanFactory, beans);\n", ClassLoadingAotContribution.class);
            code.endControlFlow();
            code.beginControlFlow("catch ($T e)", JsonProcessingException.class);
            code.add("throw new $T(e);\n", RuntimeException.class);
            code.endControlFlow();
            builder.addCode(code.build());
        });
        beanFactoryInitializationCode.addInitializer(generatedMethod.toMethodReference());
    }
}
