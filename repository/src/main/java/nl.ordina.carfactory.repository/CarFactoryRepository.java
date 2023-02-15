package nl.ordina.carfactory.repository;

import nl.ordina.carfactory.resources.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CarFactoryRepository {
    Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 5);
    Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 2);
    Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 4);

    public ArrayList<Car> getCars(){
        return Car.getCars();
    }

    public String getTesla() {
        String model  = "Tesla S3XY";
        String engine = "Electrisch";
        String color = "Metallic Blauw";
        int radius = 400;
        return String.format("Model: %s\nAandrijving: %s\nKleur: %s\nRadius %d km", model, engine, color, radius);
    }
}
