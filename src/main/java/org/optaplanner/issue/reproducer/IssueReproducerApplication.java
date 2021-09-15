package org.optaplanner.issue.reproducer;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.constructionheuristic.placer.QueuedEntityPlacerConfig;
import org.optaplanner.core.config.heuristic.selector.entity.EntitySelectorConfig;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

public class IssueReproducerApplication {
    public static void main(String[] args) {
        SolverConfig solverConfig = new SolverConfig();
        solverConfig.setSolutionClass(MyPlanningSolution.class);
        solverConfig.setScoreDirectorFactoryConfig(new ScoreDirectorFactoryConfig()
                                                           .withConstraintProviderClass(MyConstraintProvider.class));
        solverConfig.setEntityClassList(List.of(PlanningEntity1.class, PlanningEntity2.class));
        solverConfig.setTerminationConfig(new TerminationConfig().withSecondsSpentLimit(30L));
        solverConfig.setPhaseConfigList(new ArrayList<>());
        addConstructionHeuristic(solverConfig, PlanningEntity1.class);
        addConstructionHeuristic(solverConfig, PlanningEntity2.class);
        solverConfig.getPhaseConfigList().add(new LocalSearchPhaseConfig());
        SolverFactory<MyPlanningSolution> solverFactory = SolverFactory.create(solverConfig);
        Solver<MyPlanningSolution> solver = solverFactory.buildSolver();
        MyPlanningSolution problem = new MyPlanningSolution();
        problem.init();
        solver.solve(problem);
    }

    private static void addConstructionHeuristic(SolverConfig solverConfig, Class<?> entityClass) {
        ConstructionHeuristicPhaseConfig chPhaseConfig = new ConstructionHeuristicPhaseConfig();
        QueuedEntityPlacerConfig entityPlacerConfig = new QueuedEntityPlacerConfig();
        EntitySelectorConfig entitySelectorConfig = new EntitySelectorConfig();
        entityPlacerConfig.setEntitySelectorConfig(entitySelectorConfig);
        entitySelectorConfig.setEntityClass(entityClass);
        chPhaseConfig.setEntityPlacerConfig(entityPlacerConfig);
        solverConfig.getPhaseConfigList().add(chPhaseConfig);
    }
}
