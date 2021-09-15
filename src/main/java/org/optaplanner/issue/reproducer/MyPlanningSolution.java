package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.domain.solution.PlanningEntityProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.domain.valuerange.buildin.primint.IntValueRange;

@PlanningSolution
public class MyPlanningSolution {

    @PlanningEntityProperty
    PlanningEntity1 planningEntity1;

    @PlanningEntityProperty
    PlanningEntity2 planningEntity2;

    @ValueRangeProvider(id="intValueRange")
    IntValueRange intValueRange;

    @PlanningScore
    SimpleScore score;

    public MyPlanningSolution() {
    }

    public void init() {
        planningEntity1 = new PlanningEntity1();
        planningEntity2 = new PlanningEntity2();
        intValueRange = new IntValueRange(1,3);
    }
}
