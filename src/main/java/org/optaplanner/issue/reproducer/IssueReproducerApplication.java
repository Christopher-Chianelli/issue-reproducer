package org.optaplanner.issue.reproducer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        System.out.println(
                Arrays.stream(ExtendedEntityWithId.class.getDeclaredMethods())
                        .map(Method::getName)
                        .collect(Collectors.joining(", ")));
    }
}
