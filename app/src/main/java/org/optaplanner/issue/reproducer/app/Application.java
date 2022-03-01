package org.optaplanner.issue.reproducer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "org.optaplanner.issue.reproducer.simulation")
@EntityScan(basePackages = "org.optaplanner.issue.reproducer.simulation")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
