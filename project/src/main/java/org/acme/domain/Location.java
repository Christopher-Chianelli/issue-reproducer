package org.acme.domain;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;

public class Location {
    @PlanningId
    String id;


    public Location() {}

    public Location(String id) {
        this.id = id;
    }
}
