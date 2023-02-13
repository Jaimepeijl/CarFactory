package nl.ordina.CarFactory.app;

import model.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("nl.ordina.CarFactory.*")
public class CarFactoryApplication {
    public static void main(String[] args) {
        Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 5);
        Car toyota = new Car("Toyota", "Prius", "Grey", "Hybrid", 2);

        Car.addCar(tesla);
        Car.addCar(toyota);
        SpringApplication.run(CarFactoryApplication.class, args);
    }
}
