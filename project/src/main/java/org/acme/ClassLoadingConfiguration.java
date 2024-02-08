package org.acme;

import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RegisterReflectionForBinding(MyAnnotatedClass.class)
public class ClassLoadingConfiguration {
    @Bean
    public static ClassLoadingPostProcessor getClassLoadingPostProcessor() {
        return new ClassLoadingPostProcessor();
    }
}
