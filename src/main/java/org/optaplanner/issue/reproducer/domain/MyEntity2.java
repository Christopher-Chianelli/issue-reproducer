package org.optaplanner.issue.reproducer.domain;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.variable.PlanningVariableGraphType;

@PlanningEntity
public class MyEntity2 extends BaseClass {
    String code;
    BaseClass entity2Value;

    public MyEntity2() {

    }

    public MyEntity2(String code) {
        this.code = code;
    }

    @PlanningVariable(valueRangeProviderRefs = {"entity1Range", "entity2Range", "valueRange"},
                      graphType= PlanningVariableGraphType.CHAINED)
    public BaseClass getEntity2Value() {
        return entity2Value;
    }

    public void setEntity2Value(BaseClass entity2Value) {
        this.entity2Value = entity2Value;
    }
}
