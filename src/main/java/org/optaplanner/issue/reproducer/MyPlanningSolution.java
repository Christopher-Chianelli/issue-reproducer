package org.optaplanner.issue.reproducer;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.domain.valuerange.buildin.primint.IntValueRange;

@PlanningSolution
public class MyPlanningSolution {
    @PlanningEntityCollectionProperty
    List<MyPlanningEntity> planningEntityList;

    @ValueRangeProvider(id="valueRange")
    IntValueRange valueRange = new IntValueRange(0, 1);

    @PlanningScore
    SimpleScore simpleScore;
}
