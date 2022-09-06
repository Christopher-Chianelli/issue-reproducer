package org.optaplanner.issue.reproducer;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.StartupEvent;
import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.issue.reproducer.domain.MyEntity1;
import org.optaplanner.issue.reproducer.domain.MyEntity2;
import org.optaplanner.issue.reproducer.domain.Anchor;
import org.optaplanner.issue.reproducer.domain.MySolution;

@ApplicationScoped
public class IssueReproducerApplication {
    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    SolverFactory<MySolution> solverFactory;

    void onStart(@Observes StartupEvent ev) {
        List<Anchor> valueList = List.of(new Anchor("v1"),
                                          new Anchor("v2"),
                                          new Anchor("v3"));
        List<MyEntity2> entity1List = List.of(
                new MyEntity2("e1a"),
                new MyEntity2("e1b")
        );
        List<MyEntity1> entity2List = List.of(
                new MyEntity1("e2a"),
                new MyEntity1("e2b"),
                new MyEntity1("e2c")
        );
        MySolution problem = new MySolution(entity1List, entity2List, valueList);
        Solver<MySolution> solver = solverFactory.buildSolver();
        MySolution solution = solver.solve(problem);

        LOGGER.info(solution.toString());
    }
}
