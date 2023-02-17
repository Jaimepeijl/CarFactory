package nl.ordina.carfactory.app;

import nl.ordina.carfactory.repository.model.Car;
import nl.ordina.carfactory.repository.model.Phone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.ordina.carfactory")
public class CarFactoryApplication {
    public static void main(String[] args) {
        Car.cars();
        Phone.phones();
        SpringApplication.run(CarFactoryApplication.class, args);
    }
}
