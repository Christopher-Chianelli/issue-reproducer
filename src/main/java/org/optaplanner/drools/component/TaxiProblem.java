package org.optaplanner.drools.component;

import java.util.List;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfigurationProvider;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.impl.domain.valuerange.buildin.primint.IntValueRange;

@PlanningSolution
public class TaxiProblem {
    @PlanningEntityCollectionProperty
    private List<TaxiRide> taxiRideList;

    @ProblemFactProperty
    @ValueRangeProvider(id = "possibleFares")
    private IntValueRange possibleFareRange;

    @PlanningScore
    private SimpleScore score;

    @ConstraintConfigurationProvider
    private TaxiConstraintConfiguration constraintConfiguration = new TaxiConstraintConfiguration();

    public List<TaxiRide> getTaxiRideList() {
        return taxiRideList;
    }

    public void setTaxiRideList(List<TaxiRide> taxiRideList) {
        this.taxiRideList = taxiRideList;
    }

    public IntValueRange getPossibleFareRange() {
        return possibleFareRange;
    }

    public void setPossibleFareRange(IntValueRange possibleFareRange) {
        this.possibleFareRange = possibleFareRange;
    }

    public SimpleScore getScore() {
        return score;
    }

    public void setScore(SimpleScore score) {
        this.score = score;
    }

    public TaxiConstraintConfiguration getConstraintConfiguration() {
        return constraintConfiguration;
    }

    public void setConstraintConfiguration(TaxiConstraintConfiguration constraintConfiguration) {
        this.constraintConfiguration = constraintConfiguration;
    }
}
