package org.optaplanner.issue.reproducer.simulation;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

@PlanningSolution
public class MyPlanningSolution {
    @PlanningEntityCollectionProperty
    public List<MyPlanningEntity> planningEntityList;

    @ValueRangeProvider(id = "valueRange")
    public List<Integer> valueList;

    @PlanningScore
    public SimpleScore score;

    public MyPlanningSolution() {
    }

    public MyPlanningSolution(List<MyPlanningEntity> planningEntityList,
            List<Integer> valueList) {
        this.planningEntityList = planningEntityList;
        this.valueList = valueList;
    }
}
