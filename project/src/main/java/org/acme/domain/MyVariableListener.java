package org.acme.domain;

import ai.timefold.solver.core.api.domain.variable.ListVariableListener;
import ai.timefold.solver.core.api.score.buildin.simple.SimpleScore;
import ai.timefold.solver.core.api.score.director.ScoreDirector;
import ai.timefold.solver.core.impl.domain.entity.descriptor.EntityDescriptor;
import ai.timefold.solver.core.impl.domain.solution.descriptor.SolutionDescriptor;
import ai.timefold.solver.core.impl.domain.variable.descriptor.ListVariableDescriptor;
import ai.timefold.solver.core.impl.domain.variable.inverserelation.SingletonInverseVariableSupply;
import ai.timefold.solver.core.impl.domain.variable.inverserelation.SingletonListInverseVariableDemand;
import ai.timefold.solver.core.impl.score.director.AbstractScoreDirector;
import ai.timefold.solver.core.impl.score.director.InnerScoreDirector;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyVariableListener implements ListVariableListener<MySolution, MyEntity, Location> {
    static Map<ScoreDirector<?>, Map<Location, MyEntity>> inverseForEntity = new ConcurrentHashMap<>();
    @Override
    public void afterListVariableElementUnassigned(ScoreDirector<MySolution> scoreDirector, Location location) {

    }

    @Override
    public void beforeListVariableChanged(ScoreDirector<MySolution> scoreDirector, MyEntity myEntity, int start, int end) {
        AbstractScoreDirector<MySolution, SimpleScore, ?> innerScoreDirector = (AbstractScoreDirector<MySolution, SimpleScore, ?>) scoreDirector;
        SingletonInverseVariableSupply inverseVariableSupply = innerScoreDirector.getSupplyManager().demand(new SingletonListInverseVariableDemand<>(
                innerScoreDirector.getSolutionDescriptor().getListVariableDescriptors().get(0)));
        // throws here with concurrent modification exception????
        for (Location location : myEntity.locationList.subList(start, end))  {
            if (myEntity != inverseVariableSupply.getInverseSingleton(location)) {
                throw new IllegalStateException("Should never happen");
            }
        }
    }

    @Override
    public void afterListVariableChanged(ScoreDirector<MySolution> scoreDirector, MyEntity myEntity, int start, int end) {
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<MySolution> scoreDirector, MyEntity myEntity) {

    }

    @Override
    public void afterEntityAdded(ScoreDirector<MySolution> scoreDirector, MyEntity myEntity) {

    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<MySolution> scoreDirector, MyEntity myEntity) {

    }

    @Override
    public void afterEntityRemoved(ScoreDirector<MySolution> scoreDirector, MyEntity myEntity) {

    }
}
