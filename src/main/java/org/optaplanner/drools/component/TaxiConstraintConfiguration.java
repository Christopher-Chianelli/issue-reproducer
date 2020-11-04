package org.optaplanner.drools.component;

import org.optaplanner.core.api.domain.constraintweight.ConstraintConfiguration;
import org.optaplanner.core.api.domain.constraintweight.ConstraintWeight;
import org.optaplanner.core.api.score.buildin.simple.SimpleScore;

@ConstraintConfiguration
public class TaxiConstraintConfiguration {
    @ConstraintWeight("Calculate Taxi Fare")
    SimpleScore calculateTaxiFare = SimpleScore.ONE;

    public SimpleScore getCalculateTaxiFare() {
        return calculateTaxiFare;
    }

    public void setCalculateTaxiFare(SimpleScore calculateTaxiFare) {
        this.calculateTaxiFare = calculateTaxiFare;
    }
}
