package org.optaplanner.drools.component;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.impl.domain.valuerange.buildin.primint.IntValueRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class TaxiFareCalculatorService implements ApplicationRunner {
    @Autowired
    private SolverManager<TaxiProblem, Long> solverManager;

    public void calculateFare() throws InterruptedException, ExecutionException {
        TaxiProblem taxiProblem = new TaxiProblem();
        TaxiRide taxiRide = new TaxiRide();

        taxiProblem.setPossibleFareRange(new IntValueRange(0, 10));
        taxiProblem.setTaxiRideList(new ArrayList<>());

        taxiRide.setDistanceInMile(5);
        taxiProblem.getTaxiRideList().add(taxiRide);

        SolverJob<TaxiProblem, Long> job = solverManager.solve(0L, taxiProblem);
        TaxiProblem finalSol = job.getFinalBestSolution();

        System.out.println(finalSol.getScore());
    }

    @Override
    public void run(ApplicationArguments args) throws InterruptedException, ExecutionException {
        calculateFare();
    }

}
