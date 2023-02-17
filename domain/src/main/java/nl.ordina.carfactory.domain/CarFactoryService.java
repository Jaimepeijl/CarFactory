package nl.ordina.carfactory.domain;

import nl.ordina.carfactory.repository.model.CarDto;
import nl.ordina.carfactory.repository.repository.CarFactoryRepository;
import nl.ordina.carfactory.repository.model.Car;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class CarFactoryService {

    public final CarFactoryRepository carFactoryRepository;

    public CarFactoryService(CarFactoryRepository carFactoryRepository) {
        this.carFactoryRepository = carFactoryRepository;
    }

    public boolean updateStock(String carName, int amount){

        int minStock = 5;
        Car car = getCarByName(carName);
        if (car == getCarByName("tesla")){
            minStock = 2;
        } else if (car == getCarByName("toyota")) {
            minStock = 1;
        } else if (car == getCarByName("ford")) {
            minStock = 4;
        }
        if (car.getStock() - amount >= minStock){
        car.setStock(car.getStock() - amount);
        return true;
        }
        return false;
    }
    public Car getCarByName(String carName){

        for (int i = 0; i < getCars().size(); i++) {
            String car = getCars().get(i).getBrand().toLowerCase();
            if (Objects.equals(carName, car)){
                return getCars().get(i);
            }
        }
        return null;
    }

    public List<Car> getCars(){
        return carFactoryRepository.getCars();
    }
    public String getCarsString(){
        return carFactoryRepository.getCars().toString();
    }

    public String getTesla() {
        return carFactoryRepository.getTesla();
    }
}
