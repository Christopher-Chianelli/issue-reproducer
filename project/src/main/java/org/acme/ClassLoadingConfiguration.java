package org.acme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassLoadingConfiguration {
    @Bean
    public static ClassLoadingPostProcessor getClassLoadingPostProcessor() {
        return new ClassLoadingPostProcessor();
    }
}
