package nl.ordina.CarFactory.repository;

import model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarFactoryRepository {

    public String getCars(){
        return Car.printCars();
    }
}
