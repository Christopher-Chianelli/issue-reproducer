package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class PlanningEntity1 implements HasInt {

    @PlanningVariable(valueRangeProviderRefs = "intValueRange")
    public Integer value;

    @Override
    public Integer getValue() {
        return value;
    }
}
