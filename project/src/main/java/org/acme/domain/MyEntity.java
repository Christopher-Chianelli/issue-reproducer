package org.acme.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningListVariable;
import ai.timefold.solver.core.api.domain.variable.ShadowVariable;

import java.util.ArrayList;
import java.util.List;

@PlanningEntity
public class MyEntity {
    @PlanningId
    String id;
    @PlanningListVariable
    List<Location> locationList;

    @ShadowVariable(variableListenerClass = MyVariableListener.class, sourceVariableName = "locationList")
    Location calculated;

    public MyEntity() {}

    public MyEntity(String id) {
        this.id = id;
        this.locationList = new ArrayList<>();
    }
}
