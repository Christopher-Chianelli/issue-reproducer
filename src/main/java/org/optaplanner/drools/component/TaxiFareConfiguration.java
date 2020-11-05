package org.optaplanner.drools.component;

import org.drools.modelcompiler.ExecutableModelProject;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.optaplanner.drools.component")
public class TaxiFareConfiguration {
    private static final String drlFile = "TAXI_FARE_RULE.drl";

    @Bean
    public KieContainer kieContainer() {
        KieServices kieServices = KieServices.Factory.get();
        KieResources kieResources = kieServices.getResources();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        ClassLoader actualClassLoader = Thread.currentThread().getContextClassLoader();
        kieFileSystem.write(kieResources.newClassPathResource(drlFile, "UTF-8", actualClassLoader));
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll(ExecutableModelProject.class);
        KieModule kieModule = kieBuilder.getKieModule();

        return kieServices.newKieContainer(kieModule.getReleaseId());
    }
}
