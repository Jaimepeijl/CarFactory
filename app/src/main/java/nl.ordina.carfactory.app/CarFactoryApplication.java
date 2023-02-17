package nl.ordina.carfactory.app;

import nl.ordina.carfactory.repository.model.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.ordina.carfactory")
public class CarFactoryApplication {
    public static void main(String[] args) {
        Car.cars();
        SpringApplication.run(CarFactoryApplication.class, args);
    }
}
