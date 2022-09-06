package org.optaplanner.issue.reproducer.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class MyEntity1 extends BaseClass {
    String code;
    BaseClass entity1Value;

    public MyEntity1() {

    }

    public MyEntity1(String code) {
        this.code = code;
    }

    @PlanningVariable(valueRangeProviderRefs = {"entity1Range", "entity2Range", "valueRange"},
                      graphType= PlanningVariableGraphType.CHAINED)
    public BaseClass getEntity1Value() {
        return entity1Value;
    }

    public void setEntity1Value(BaseClass entity1Value) {
        this.entity1Value = entity1Value;
    }
}
