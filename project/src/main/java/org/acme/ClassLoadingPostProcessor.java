package org.acme;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassLoadingPostProcessor implements ApplicationContextAware, BeanFactoryPostProcessor {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        EntityScanner entityScanner = new EntityScanner(context);
        try {
            Set<Class<?>> classSet = entityScanner.scan(MyAnnotation.class);
            if (classSet.isEmpty()) {
                // AOT Processor will handle it
                return;
            }
            for (Class<?> clazz : classSet) {
                MyAnnotatedClass instance = (MyAnnotatedClass) clazz.getConstructor().newInstance();
                instance.setType(clazz);
                beanFactory.registerSingleton(clazz.getSimpleName(), instance);
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
