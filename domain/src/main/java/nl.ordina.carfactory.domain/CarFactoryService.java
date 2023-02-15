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

    public boolean updateStock(Car car, int stock){
        return car.getStock() - stock >= 5;
    }

    public List<Car> getCars(){
        return carFactoryRepository.getCars();
    }

    public String getTesla() {
        return carFactoryRepository.getTesla();
    }
}
