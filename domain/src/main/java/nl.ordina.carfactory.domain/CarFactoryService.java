package nl.ordina.carfactory.domain;

import nl.ordina.carfactory.repository.CarFactoryRepository;
import nl.ordina.carfactory.resources.Car;
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
