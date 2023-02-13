package nl.ordina.CarFactory.repository;

import model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarFactoryRepository {

    public String getCars(){
        return Car.printCars();
    }

    public String getTesla() {
        String model  = "Tesla S3XY";
        String engine = "Electrisch";
        String color = "Metallic Blauw";
        int radius = 400;
        return String.format("Model: %s\nAandrijving: %s\nKleur: %s\nRadius %d km", model, engine, color, radius);
    }
}
