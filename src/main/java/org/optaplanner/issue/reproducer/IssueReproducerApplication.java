package org.optaplanner.issue.reproducer;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        MyEntity a = new MyEntity("A", true);
        MyEntity b = new MyEntity("B", true);
        MyEntity c = new MyEntity("C", false);

        a.persist();
        b.persist();
        c.persist();

        List<MyEntity> shouldBeEmpty = MyEntity.getEntitiesWithNamesAndLifeStatus(0L, Collections.emptySet(), false);
        LOGGER.info(shouldBeEmpty.stream().map(MyEntity::toString).collect(Collectors.joining(", ", "[", "]")));
    }
}
