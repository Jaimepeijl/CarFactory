package nl.ordina.distribution.app;

import nl.ordina.distribution.repository.model.Car;
import nl.ordina.distribution.repository.model.Laptop;
import nl.ordina.distribution.repository.model.Phone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.ordina.distribution.*")
public class DistributionApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributionApplication.class, args);
    }
}
