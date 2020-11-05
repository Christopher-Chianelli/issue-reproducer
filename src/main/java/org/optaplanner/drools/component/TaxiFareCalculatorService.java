package org.optaplanner.drools.component;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class TaxiFareCalculatorService implements ApplicationRunner {
    @Autowired
    private KieContainer kieContainer;

    public void calculateFare() {
        Fare rideFare = new Fare();
        TaxiRide taxiRide = new TaxiRide();

        taxiRide.setDistanceInMile(5);

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("rideFare", rideFare);
        kieSession.insert(taxiRide);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println(rideFare.getRideFare());
    }

    @Override
    public void run(ApplicationArguments args) {
        calculateFare();
    }

}
