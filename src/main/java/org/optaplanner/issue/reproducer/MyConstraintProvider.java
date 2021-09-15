package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class MyConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                maximizeSum(constraintFactory),
        };
    }

    public Constraint maximizeSum(ConstraintFactory constraintFactory) {
        return constraintFactory.from(HasInt.class)
                .groupBy(ConstraintCollectors.sum(HasInt::getValue))
                .reward("Maximize Sum", SimpleScore.ONE);
    }
}
