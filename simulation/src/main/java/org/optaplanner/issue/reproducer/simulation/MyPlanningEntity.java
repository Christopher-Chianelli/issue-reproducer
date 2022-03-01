package org.optaplanner.issue.reproducer.simulation;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.lookup.PlanningId;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class MyPlanningEntity {
    @PlanningId
    public String code;

    @PlanningVariable(valueRangeProviderRefs = "valueRange")
    public Integer value;

    public MyPlanningEntity() {

    }

    public MyPlanningEntity(String code) {
        this(code, null);
    }

    public MyPlanningEntity(String code, Integer value) {
        this.code = code;
        this.value = value;
    }
}
