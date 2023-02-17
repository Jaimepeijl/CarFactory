package nl.ordina.distribution.app;

import nl.ordina.distribution.repository.model.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.ordina.distribution")
public class DistributionApplication {
    public static void main(String[] args) {
        Car.cars();
        SpringApplication.run(DistributionApplication.class, args);
    }
}
