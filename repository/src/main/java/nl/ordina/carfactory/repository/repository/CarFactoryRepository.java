package nl.ordina.carfactory.repository.repository;


import nl.ordina.carfactory.repository.model.Car;
import nl.ordina.carfactory.repository.model.Phone;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CarFactoryRepository {

    public ArrayList<Car> getCars(){
        return Car.getCars();
    }
    public ArrayList<Phone> getPhones(){
        return Phone.getPhones();
    }

    public String getTesla() {
        String model  = "Tesla S3XY";
        String engine = "Electrisch";
        String color = "Metallic Blauw";
        int radius = 400;
        return String.format("Model: %s\nAandrijving: %s\nKleur: %s\nRadius %d km", model, engine, color, radius);
    }
}
