package org.optaplanner.issue.reproducer;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.optaplanner.core.api.score.stream.ConstraintStreamImplType;
import org.optaplanner.core.api.solver.SolverFactory;

import io.quarkus.runtime.StartupEvent;
import org.optaplanner.core.config.solver.SolverConfig;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    SolverConfig solverConfig;

    void onStart(@Observes StartupEvent ev) {
        User user_alice = new User("Alice", 100, 45);
        User user_bob = new User("Bob", 90, 54);
        User user_chris = new User("Chris", 80, 56);
        User user_dave = new User("Dave", 80, 52);

        List<Integer> selectedList = List.of(0, 1);
        List<Job> jobList = List.of(
                new Job("1", "Job2", "Leader", user_bob, 10),
                new Job("2", "Job2", "Leader", user_chris, 10),
                new Job("3", "Job3", "Leader", user_alice, 5),
                new Job("4", "Job3", "Leader", user_bob, 5),
                new Job("5", "Job6", "Leader", user_alice, 15),
                new Job("6", "Job6", "Leader", user_bob, 15),
                new Job("7", "Job6", "Leader", user_dave, 15),
                new Job("8", "Job7", "Leader", user_bob, 10),
                new Job("9", "Job7", "Leader", user_dave, 10));
        jobList.get(0).setSelected(selectedList.get(0));

        SolverConfig droolsConfig = solverConfig.copyConfig();
        droolsConfig.getScoreDirectorFactoryConfig().setConstraintStreamImplType(ConstraintStreamImplType.DROOLS);

        SolverConfig bavetConfig = solverConfig.copyConfig();
        bavetConfig.getScoreDirectorFactoryConfig().setConstraintStreamImplType(ConstraintStreamImplType.BAVET);


        // Bavet works
        SolverFactory<Assignments> bavetSolverFactory = SolverFactory.create(bavetConfig);
        Assignments bavetAssignments = new Assignments(selectedList, jobList);
        bavetSolverFactory.buildSolver().solve(bavetAssignments);

        // Drools throws
        SolverFactory<Assignments> droolsSolverFactory = SolverFactory.create(droolsConfig);
        Assignments droolsAssignments = new Assignments(selectedList, jobList);
        droolsSolverFactory.buildSolver().solve(droolsAssignments);
    }
}
