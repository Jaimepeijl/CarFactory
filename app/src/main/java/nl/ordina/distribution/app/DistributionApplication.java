package nl.ordina.distribution.app;

import nl.ordina.distribution.repository.model.Car;
import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.ordina.distribution")
public class DistributionApplication {
    public static void main(String[] args) {
        Car.cars();
        nl.ordina.carfactory.repository.model.Phone.phones();
        Laptop.laptops();
        SpringApplication.run(DistributionApplication.class, args);
    }
}
