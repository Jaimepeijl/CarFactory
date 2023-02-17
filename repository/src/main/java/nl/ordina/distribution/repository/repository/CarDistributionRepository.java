package nl.ordina.distribution.repository.repository;


import nl.ordina.distribution.repository.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CarDistributionRepository {

    public ArrayList<Car> getCars(){
        return Car.getCars();
    }
    public ArrayList<nl.ordina.carfactory.repository.model.Phone> getPhones(){
        return nl.ordina.carfactory.repository.model.Phone.getPhones();
    }

    public String getTesla() {
        String model  = "Tesla S3XY";
        String engine = "Electrisch";
        String color = "Metallic Blauw";
        int radius = 400;
        return String.format("Model: %s\nAandrijving: %s\nKleur: %s\nRadius %d km", model, engine, color, radius);
    }
}
