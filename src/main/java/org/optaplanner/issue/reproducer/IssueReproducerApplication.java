package org.optaplanner.issue.reproducer;

import java.util.logging.Logger;

import org.drools.ancompiler.KieBaseUpdaterANC;
import org.drools.core.io.impl.ClassPathResource;
import org.drools.modelcompiler.ExecutableModelProject;
import org.kie.api.KieBase;
import org.kie.api.conf.KieBaseMutabilityOption;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.builder.conf.PropertySpecificOption;
import org.kie.internal.utils.KieHelper;

public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    public static void main(String... args) {
        KieBase kieBase;
        KieHelper kieHelper = new KieHelper(PropertySpecificOption.ALLOWED)
                .setClassLoader(IssueReproducerApplication.class.getClassLoader());

        kieHelper.addResource(new ClassPathResource("constraints.drl", IssueReproducerApplication.class.getClassLoader()));

        try {
            kieBase = kieHelper.build(ExecutableModelProject.class, KieBaseMutabilityOption.DISABLED);
        } catch (Exception ex) {
            throw new IllegalStateException("There is an error in a scoreDrl or scoreDrlFile.", ex);
        }

        KieBaseUpdaterANC.generateAndSetInMemoryANC(kieBase);
        KieBaseUpdaterANC.generateAndSetInMemoryANC(kieBase);
        KieSession kieSession = kieBase.newKieSession();
        Person person = new Person("James");
        FactHandle factHandle = kieSession.insert(person);
        kieSession.fireAllRules();
        person.setName("James Bond");
        kieSession.update(factHandle, person, "name");
        kieSession.fireAllRules();
    }
}
