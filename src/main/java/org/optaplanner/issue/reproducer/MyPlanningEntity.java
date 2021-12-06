package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class MyPlanningEntity {
    @PlanningVariable(valueRangeProviderRefs = "valueRange")
    Integer planningValue;
}
