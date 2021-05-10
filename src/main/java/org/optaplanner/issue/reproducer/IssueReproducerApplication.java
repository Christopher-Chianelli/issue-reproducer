package org.optaplanner.issue.reproducer;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.StartupEvent;
import org.drools.ancompiler.KieBaseUpdaterANC;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.kogito.rules.KieRuntimeBuilder;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject KieRuntimeBuilder kieRuntimeBuilder;

    void onStart(@Observes StartupEvent ev) throws ExecutionException, InterruptedException {
        KieBase kieBase = kieRuntimeBuilder.getKieBase();
        KieBaseUpdaterANC.generateAndSetInMemoryANC(kieBase);
        KieBaseUpdaterANC.generateAndSetInMemoryANC(kieBase);
        KieSession kieSession = kieRuntimeBuilder.newKieSession();
        Person person = new Person("James");
        FactHandle factHandle = kieSession.insert(person);
        kieSession.fireAllRules();
        person.setName("James Bond");
        kieSession.update(factHandle, person, "name");
        kieSession.fireAllRules();
    }
}
