package org.optaplanner.issue.reproducer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.StartupEvent;
import org.optaplanner.core.api.solver.SolverManager;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    SolverManager<MyPlanningSolution, Long> solverManager;

    void onStart(@Observes StartupEvent ev) throws ExecutionException, InterruptedException {
        MyPlanningSolution problem = new MyPlanningSolution();
        problem.planningEntityList = new ArrayList<>();
        problem.planningEntityList.addAll(List.of(new MyPlanningEntity(), new MyPlanningEntity()));
        MyPlanningSolution solution = solverManager.solve(1L, problem).getFinalBestSolution();
        System.out.println("Entity 1 value: " + solution.planningEntityList.get(0).planningValue);
        System.out.println("Entity 2 value: " + solution.planningEntityList.get(1).planningValue);
        System.out.println("Score: " + solution.simpleScore);
    }
}
