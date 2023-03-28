package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.repository.CarDistributionRepository;
import nl.ordina.distribution.repository.model.Car;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CarDistributionService {

    public final CarDistributionRepository carDistributionRepository;

    public CarDistributionService(CarDistributionRepository carDistributionRepository) {
        this.carDistributionRepository = carDistributionRepository;
    }

    public int updateStock(CarDto carDto){
        int orderAmount = carDto.orderAmount();
        if (carDto.uuid() !=  null){
            Car car = getCarById(carDto.uuid());
            if (car.getStock() - orderAmount < car.getMinStock()){
                return 0;
            }
            car.setStock(car.getStock() - orderAmount);
            carDistributionRepository.save(car);
            return car.getStock();
        }
        if (carDto.colour() != null){
            Car car = carDistributionRepository.findCarByBrandEqualsIgnoreCaseAndColourEqualsIgnoreCase(carDto.name(), carDto.colour());
            if (car.getStock() - orderAmount < car.getMinStock()){
                return 0;
            }
            car.setStock(car.getStock() - orderAmount);
            carDistributionRepository.save(car);
            return car.getStock();
        }
        if (carDistributionRepository.findCarByBrandEqualsIgnoreCase(carDto.name()) != null){
            Car car = getCarByName(carDto.name());
            if (car.getStock() - orderAmount < car.getMinStock()){
                return 0;
            }
            car.setStock(car.getStock() - orderAmount);
            carDistributionRepository.save(car);
            return car.getStock();
        }
        return -1;
    }



    public Car getCarByName(String carName){
        return carDistributionRepository.findCarByBrandEqualsIgnoreCase(carName);
    }
    public Car getCarById(UUID id){
        return carDistributionRepository.findCarById(id);
    }

    public List<Car> getCars(){
        return carDistributionRepository.findAll();
    }
}
