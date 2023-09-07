package org.acme.domain;

import ai.timefold.solver.core.impl.heuristic.move.Move;
import ai.timefold.solver.core.impl.heuristic.selector.move.factory.MoveListFactory;

import java.util.List;

public class MyMoveListFactory implements MoveListFactory<MySolution> {
    @Override
    public List<? extends Move<MySolution>> createMoveList(MySolution mySolution) {
        return mySolution.entityList.stream()
                .map(MyMove::new).toList();
    }
}
