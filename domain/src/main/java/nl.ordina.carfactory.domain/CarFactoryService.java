package nl.ordina.carfactory.domain;

import nl.ordina.carfactory.repository.repository.CarFactoryRepository;
import nl.ordina.carfactory.repository.model.Car;
import org.springframework.stereotype.Service;


import java.util.List;

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
    public Car getCarByName(String carName){

        for (int i = 0; i < getCars().size(); i++) {
            if (carName == getCars().get(i).getBrand()){
                break;
            } return getCars().get(i);
        }
        return null;
    }

    public List<Car> getCars(){
        return carFactoryRepository.getCars();
    }

    public String getTesla() {
        return carFactoryRepository.getTesla();
    }

    Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 5);
    Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 2);
    Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 4);

//        Car.addCar(tesla);
//        Car.addCar(toyota);
//        Car.addCar(ford);
}
