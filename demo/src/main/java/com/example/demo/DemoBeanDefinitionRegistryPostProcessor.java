package com.example.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class DemoBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    final boolean USE_WORKAROUND = true;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(MyBean.class);
        beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(new MyBeanRecord("Bob", 12));
        registry.registerBeanDefinition("MyBean", beanDefinition);
    }
}
