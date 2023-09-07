package org.acme;

import ai.timefold.solver.core.api.score.buildin.simple.SimpleScore;
import ai.timefold.solver.core.api.score.calculator.EasyScoreCalculator;
import org.acme.domain.MySolution;

public class MyScoreCalculator implements EasyScoreCalculator<MySolution, SimpleScore> {
    @Override
    public SimpleScore calculateScore(MySolution mySolution) {
        return SimpleScore.ZERO;
    }
}
