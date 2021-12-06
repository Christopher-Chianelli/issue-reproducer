package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.score.buildin.simple.SimpleScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.core.api.score.stream.Joiners;

public class MyConstraintProvider implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                constraintFactory.forEach(MyPlanningEntity.class)
                        .join(MyPlanningEntity.class, Joiners.equal(entity -> new MyComparable(entity.planningValue)))
                        .reward("Same Value", SimpleScore.ONE)
        };
    }
}
