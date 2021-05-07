package org.optaplanner.issue.reproducer;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.StartupEvent;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.kogito.rules.KieRuntimeBuilder;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject KieRuntimeBuilder kieRuntimeBuilder;

    void onStart(@Observes StartupEvent ev) throws ExecutionException, InterruptedException {
        KieSession kieSession = kieRuntimeBuilder.newKieSession();
        Person person = new Person("James", "B.");
        FactHandle factHandle = kieSession.insert(person);
        kieSession.fireAllRules();
        person.setLastName("Bond");
        kieSession.update(factHandle, person, "lastName");
        kieSession.fireAllRules();
    }
}
