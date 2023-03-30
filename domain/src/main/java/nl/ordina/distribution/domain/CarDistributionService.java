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
        Car car = getCar(carDto);
        if (car == null){
            return -1;
        }
        if (car.getStock() - orderAmount < car.getMinStock()){
            return 0;
        }
        car.setStock(car.getStock() - orderAmount);
        carDistributionRepository.save(car);
        return car.getStock();
    }
    public Car getCar(CarDto carDto) {
        if (carDto.uuid() != null) {
            return getCarById(carDto.uuid());
        } else if (carDto.colour() != null) {
            return getCarByBrandAndColour(carDto.name(), carDto.colour());
        } else {
            return getCarByBrand(carDto.name());
        }
    }
    public Car getCarByBrand(String carName){
        return carDistributionRepository.findCarByBrandEqualsIgnoreCase(carName);
    }
    public Car getCarById(UUID id){
        return carDistributionRepository.findCarById(id);
    }
    public Car getCarByBrandAndColour(String carModel, String carColour){
        return carDistributionRepository.findCarByBrandEqualsIgnoreCaseAndColourEqualsIgnoreCase(carModel, carColour);
    }

    public List<Car> getCars(){
        return carDistributionRepository.findAll();
    }
}
