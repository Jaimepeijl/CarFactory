package nl.ordina.CarFactory.domain;

import nl.ordina.CarFactory.repository.CarFactoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CarFactoryService {

    public final CarFactoryRepository carFactoryRepository;

    public CarFactoryService(CarFactoryRepository carFactoryRepository) {
        this.carFactoryRepository = carFactoryRepository;
    }

    public String getCars(){
        return carFactoryRepository.getCars();
    }
}
