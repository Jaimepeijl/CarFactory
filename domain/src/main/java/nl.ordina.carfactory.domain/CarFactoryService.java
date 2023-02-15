package nl.ordina.carfactory.domain;

import nl.ordina.carfactory.repository.CarFactoryRepository;
import nl.ordina.carfactory.resources.Car;
import nl.ordina.carfactory.resources.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarFactoryService {

    public final CarFactoryRepository carFactoryRepository;

    public CarFactoryService(CarFactoryRepository carFactoryRepository) {
        this.carFactoryRepository = carFactoryRepository;
    }

    public boolean updateStock(Car car, int amount){
        if (car.getStock() - amount > 5){
        car.setStock(car.getStock() - amount);
        return true;
        }
        return false;
    }
    public Car getCarByName(CarDto carName){

        for (int i = 0; i < getCars().size(); i++) {
            String car = getCars().get(i).getBrand().toLowerCase();
            if (Objects.equals(carName.getName(), car)){
                return getCars().get(i);
            }
        }
        return null;
    }

    public List<Car> getCars(){
        return carFactoryRepository.getCars();
    }

    public String getTesla() {
        return carFactoryRepository.getTesla();
    }
}
