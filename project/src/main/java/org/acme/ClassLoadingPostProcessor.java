package org.acme;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
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

/**
 * This class should be implemented as a {@link BeanDefinitionRegistryPostProcessor}
 * (see <a href="https://github.com/Christopher-Chianelli/issue-reproducer/pull/1">this PR</a>).
 * However, to show that {@link EntityScanner} returns nothing in native at runtime
 * (as now noted in the
 * <a href="https://docs.spring.io/spring-framework/reference/core/aot.html">the Spring AOT docs</a>),
 * it is implemented as a {@link BeanFactoryPostProcessor} that uses an {@link EntityScanner}
 * to register classes.
 */
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
                throw new IllegalStateException("Unable to find any classes annotated with " + MyAnnotation.class);
            }
            for (Class<?> clazz : classSet) {
                beanFactory.registerSingleton(clazz.getSimpleName(), clazz.getConstructor().newInstance());
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
