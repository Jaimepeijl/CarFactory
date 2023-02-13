package nl.ordina.CarFactory.repository;

import org.springframework.stereotype.Repository;

import java.util.StringJoiner;

@Repository
public class CarFactoryRepository {

    public String getCars() {
        return "All cars";
    }

    public String getTesla() {
        String model  = "Tesla S3XY";
        String engine = "Electrisch";
        String color = "Metallic Blauw";
        int radius = 400;
        return String.format("Model: %s\nAandrijving: %s\nKleur: %s\nRadius %d km", model, color, engine, radius);
    }
}

