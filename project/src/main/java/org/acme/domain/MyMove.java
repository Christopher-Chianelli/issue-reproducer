package org.acme.domain;

import ai.timefold.solver.core.api.score.buildin.simple.SimpleScore;
import ai.timefold.solver.core.api.score.director.ScoreDirector;
import ai.timefold.solver.core.impl.domain.variable.inverserelation.SingletonInverseVariableSupply;
import ai.timefold.solver.core.impl.domain.variable.inverserelation.SingletonListInverseVariableDemand;
import ai.timefold.solver.core.impl.heuristic.move.AbstractMove;
import ai.timefold.solver.core.impl.heuristic.move.Move;
import ai.timefold.solver.core.impl.score.director.AbstractScoreDirector;

public class MyMove extends AbstractMove<MySolution> {
    MyEntity myEntity;

    public MyMove(MyEntity myEntity) {
        this.myEntity = myEntity;
    }

    @Override
    protected AbstractMove<MySolution> createUndoMove(ScoreDirector<MySolution> scoreDirector) {
        return this;
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<MySolution> scoreDirector) {

    }

    @Override
    public boolean isMoveDoable(ScoreDirector<MySolution> scoreDirector) {
        return true;
    }

    @Override
    public Move<MySolution> rebase(ScoreDirector<MySolution> scoreDirector) {
        AbstractScoreDirector<MySolution, SimpleScore, ?> innerScoreDirector = (AbstractScoreDirector<MySolution, SimpleScore, ?>) scoreDirector;

        MyEntity rebasedEntity = innerScoreDirector.lookUpWorkingObject(myEntity);
        if (rebasedEntity != myEntity) {
            for (Location location : myEntity.locationList)  {
                if (location == innerScoreDirector.lookUpWorkingObject(location)) {
                    throw new IllegalStateException("Should never happen");
                }
            }
        }
        return new MyMove(rebasedEntity);
    }
}
