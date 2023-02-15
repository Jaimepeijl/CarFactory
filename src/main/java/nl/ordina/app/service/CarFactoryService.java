package nl.ordina.app.service;

import nl.ordina.app.model.Car;
import nl.ordina.app.repository.CarFactoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarFactoryService {

    public final CarFactoryRepository carFactoryRepository;

    public CarFactoryService(CarFactoryRepository carFactoryRepository) {
        this.carFactoryRepository = carFactoryRepository;
    }

    public List<Car> findAll(){
        carFactoryRepository.getCars();
        return findAll();
    }
    public void saveCar (Car car){
        Car.addCar(car);
    }

    public String getCars(){
        return carFactoryRepository.getCars();
    }

    public String getTesla() {
        return carFactoryRepository.getTesla();
    }
}
