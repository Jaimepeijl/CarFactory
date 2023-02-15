package nl.ordina.carfactory.app;

import nl.ordina.carfactory.resources.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarFactoryApplication {
    public static void main(String[] args) {
        Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 5);
        Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 2);
        Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 4);

        Car.addCar(tesla);
        Car.addCar(toyota);
        Car.addCar(ford);
        SpringApplication.run(CarFactoryApplication.class, args);
    }
}
