package org.acme;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotContribution;
import org.springframework.beans.factory.aot.BeanFactoryInitializationAotProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.domain.EntityScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Set;

public class ClassLoadingPostProcessor implements ApplicationContextAware, BeanDefinitionRegistryPostProcessor, BeanFactoryInitializationAotProcessor {

    ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        EntityScanner entityScanner = new EntityScanner(context);
        try {
            Set<Class<?>> classSet = entityScanner.scan(MyAnnotation.class);
            if (classSet.isEmpty()) {
                throw new IllegalStateException("Unable to find any classes annotated with " + MyAnnotation.class);
            }
            for (Class<?> clazz : classSet) {
                registry.registerBeanDefinition(clazz.getSimpleName(), new RootBeanDefinition(clazz));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BeanFactoryInitializationAotContribution processAheadOfTime(ConfigurableListableBeanFactory beanFactory) {
        // Nothing to do, beans have already been contributed at build-time.
        return null;
    }
}
