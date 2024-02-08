package org.acme;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Set;

public class ClassLoadingAotProcessor implements ApplicationContextAware, BeanFactoryInitializationAotProcessor {
    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public BeanFactoryInitializationAotContribution processAheadOfTime(ConfigurableListableBeanFactory beanFactory) {
        EntityScanner entityScanner = new EntityScanner(context);
        try {
            Set<Class<?>> classSet = entityScanner.scan(MyAnnotation.class);
            return new ClassLoadingAotContribution(classSet);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
