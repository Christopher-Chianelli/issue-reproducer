package org.acme;

import ai.timefold.solver.core.api.solver.Solver;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import ai.timefold.solver.core.config.heuristic.selector.move.composite.UnionMoveSelectorConfig;
import ai.timefold.solver.core.config.heuristic.selector.move.factory.MoveListFactoryConfig;
import ai.timefold.solver.core.config.heuristic.selector.move.generic.list.ListChangeMoveSelectorConfig;
import ai.timefold.solver.core.config.heuristic.selector.move.generic.list.ListSwapMoveSelectorConfig;
import ai.timefold.solver.core.config.heuristic.selector.move.generic.list.kopt.KOptListMoveSelectorConfig;
import ai.timefold.solver.core.config.localsearch.LocalSearchPhaseConfig;
import ai.timefold.solver.core.config.solver.SolverConfig;
import org.acme.domain.Location;
import org.acme.domain.MyEntity;
import org.acme.domain.MyMoveListFactory;
import org.acme.domain.MySolution;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SolverConfig solverConfig = new SolverConfig();
        solverConfig
                .withMoveThreadCount("1")
                .withEasyScoreCalculatorClass(MyScoreCalculator.class)
                .withEntityClasses(MyEntity.class)
                .withSolutionClass(MySolution.class)
                .withPhaseList(List.of(new ConstructionHeuristicPhaseConfig(),
                        new LocalSearchPhaseConfig().withMoveSelectorConfig(
                                new UnionMoveSelectorConfig(List.of(
                                    new ListChangeMoveSelectorConfig(),
                                    new ListSwapMoveSelectorConfig(),
                                    new MoveListFactoryConfig()
                                            .withMoveListFactoryClass(MyMoveListFactory.class)
                                ))
                        )));
        Solver<MySolution> solver = ((SolverFactory)(SolverFactory.create(solverConfig)))
                .buildSolver();
        solver.solve(new MySolution(List.of(new MyEntity("entity")), List.of(new Location("1"), new Location("2"), new Location("3"))));
    }
}