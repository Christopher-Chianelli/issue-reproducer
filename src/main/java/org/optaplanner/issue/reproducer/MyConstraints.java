package org.optaplanner.issue.reproducer;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;
import org.optaplanner.issue.reproducer.domain.MyEntity1;
import org.optaplanner.issue.reproducer.domain.MyEntity2;

public class MyConstraints implements ConstraintProvider {

    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] {
                constraintFactory.forEach(MyEntity2.class)
                        .penalize("Domain 1", HardSoftScore.ONE_HARD),
                constraintFactory.forEach(MyEntity1.class)
                        .penalize("Domain 2", HardSoftScore.ONE_SOFT)
        };
    }
}
