package org.acme.domain;

import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.solution.ProblemFactCollectionProperty;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.simple.SimpleScore;

import java.util.List;

@PlanningSolution
public class MySolution {
    @PlanningEntityCollectionProperty
    List<MyEntity> entityList;

    @ProblemFactCollectionProperty
    @ValueRangeProvider
    List<Location> locationList;

    @PlanningScore
    SimpleScore score;

    public MySolution() {
    }

    public MySolution(List<MyEntity> entityList, List<Location> locationList) {
        this.entityList = entityList;
        this.locationList = locationList;
    }
}
