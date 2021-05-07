package org.optaplanner.issue.reproducer;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.StartupEvent;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.solver.SolverManager;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject ScoreManager<PeopleRoster, SimpleScore> scoreManager;
    // @Inject SolverManager<PeopleRoster, Long> solverManager;

    void onStart(@Observes StartupEvent ev) throws ExecutionException, InterruptedException {
        PeopleRoster peopleRoster = new PeopleRoster();
        peopleRoster.setPersonList(Arrays.asList(
                new Person("Alice"),
                new Person("Bob")
        ));
        peopleRoster.setValueList(Arrays.asList(
                "v1",
                "v2",
                "v3"
        ));
        peopleRoster.getPersonList().get(0).setValue("v1");
        peopleRoster.getPersonList().get(1).setValue("v2");
        System.out.println(scoreManager.updateScore(peopleRoster));
        // PeopleRoster bestSolution = solverManager.solve(0L, peopleRoster).getFinalBestSolution();
        // System.out.println(bestSolution.getScore());
        // System.out.println(bestSolution);
    }
}
