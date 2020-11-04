package org.optaplanner.drools.component;

import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

@PlanningEntity
public class TaxiRide {
    @PlanningVariable(valueRangeProviderRefs = "possibleFares")
    private Integer distanceInMile;

    public Integer getDistanceInMile() {
        return distanceInMile;
    }

    public void setDistanceInMile(Integer distanceInMile) {
        this.distanceInMile = distanceInMile;
    }
}
