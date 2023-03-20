package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.CarOrder;
import nl.ordina.distribution.repository.repository.CarDistributionRepository;
import nl.ordina.distribution.repository.model.Car;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarDistributionService {

    public final CarDistributionRepository carDistributionRepository;

    public CarDistributionService(CarDistributionRepository carDistributionRepository) {
        this.carDistributionRepository = carDistributionRepository;
    }

    public int updateStock(CarDto carDto){
        int amount = carDto.stock();
        if (carDto.colour() != null){
            Car car = carDistributionRepository.findCarByBrandEqualsIgnoreCaseAndColourEqualsIgnoreCase(carDto.name(), carDto.colour());
            if (car.getStock() - amount < car.getMinStock()){
                return 0;
            }
            car.setStock(car.getStock() - amount);
            carDistributionRepository.save(car);
            return car.getStock();
        }
        if (carDistributionRepository.findCarByBrandEqualsIgnoreCase(carDto.name()) != null){
            Car car = getCarByName(carDto.name());
            if (car.getStock() - amount < car.getMinStock()){
                return 0;
            }
            car.setStock(car.getStock() - amount);
            carDistributionRepository.save(car);
            return car.getStock();
        }
        return -1;
    }

    public Car getCarByName(String carName){
        return carDistributionRepository.findCarByBrandEqualsIgnoreCase(carName);
    }

    public List<Car> getCars(){
        return carDistributionRepository.findAll();
    }
}
