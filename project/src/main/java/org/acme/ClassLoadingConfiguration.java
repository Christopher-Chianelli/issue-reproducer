package org.acme;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassLoadingConfiguration {
    @Bean
    public static ClassLoadingAotProcessor getClassLoadingAotProcessor() {
        return new ClassLoadingAotProcessor();
    }

    @Bean
    public static ClassLoadingPostProcessor getClassLoadingPostProcessor() {
        return new ClassLoadingPostProcessor();
    }
}
