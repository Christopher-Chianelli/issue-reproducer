package org.optaplanner.issue.reproducer.app;

import java.util.Arrays;
import java.util.List;

import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.issue.reproducer.simulation.MyPlanningEntity;
import org.optaplanner.issue.reproducer.simulation.MyPlanningSolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

public class MyService {
    @Autowired
    SolverManager<MyPlanningSolution, Long> solverManager;

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

            MyPlanningSolution problem = new MyPlanningSolution(
                    List.of(new MyPlanningEntity("A"), new MyPlanningEntity("B"), new MyPlanningEntity("C")),
                    List.of(1, 2, 3)
            );

            MyPlanningSolution solution = solverManager.solve(0L, problem).getFinalBestSolution();

            System.out.println(solution.score);
        };
    }
}
