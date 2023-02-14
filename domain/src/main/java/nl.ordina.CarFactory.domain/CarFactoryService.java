package nl.ordina.CarFactory.domain;

import model.Car;
import nl.ordina.CarFactory.repository.CarFactoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarFactoryService {

    public final CarFactoryRepository carFactoryRepository;

    public CarFactoryService(CarFactoryRepository carFactoryRepository) {
        this.carFactoryRepository = carFactoryRepository;
    }

    List<Car> findAll(){
        carFactoryRepository.getCars();
        return findAll();
    }

    public String getCars(){
        return carFactoryRepository.getCars();
    }

    public String getTesla() {
        return carFactoryRepository.getTesla();
    }
}
