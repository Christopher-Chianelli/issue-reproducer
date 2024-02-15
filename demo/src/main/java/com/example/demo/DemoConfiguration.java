package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {
    @Bean
    public static DemoBeanDefinitionRegistryPostProcessor getDemoBeanDefinitionRegistryPostProcessor() {
        return new DemoBeanDefinitionRegistryPostProcessor();
    }
}
