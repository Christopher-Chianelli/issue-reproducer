package org.optaplanner.issue.reproducer.domain;

import java.util.List;

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

@PlanningSolution
public class MySolution {
    List<MyEntity2> entity1List;
    List<MyEntity1> entity2List;
    List<Anchor> valueList;
    HardSoftScore score;

    public MySolution() {}

    public MySolution(List<MyEntity2> entity1List,
                      List<MyEntity1> entity2List,
                      List<Anchor> valueList) {
        this.entity1List = entity1List;
        this.entity2List = entity2List;
        this.valueList = valueList;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "entity1Range")
    public List<MyEntity2> getEntity1List() {
        return entity1List;
    }

    @PlanningEntityCollectionProperty
    @ValueRangeProvider(id = "entity2Range")
    public List<MyEntity1> getEntity2List() {
        return entity2List;
    }

    @ProblemFactCollectionProperty
    @ValueRangeProvider(id = "valueRange")
    public List<Anchor> getValueList() {
        return valueList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }
}
