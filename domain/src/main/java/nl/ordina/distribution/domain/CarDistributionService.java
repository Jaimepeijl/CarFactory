package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.NewCarDto;
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
        if (carDistributionRepository.existsById(carDto.name().toLowerCase())){
            Car car = getCarByName(carDto.name().toLowerCase());
            if (car.getStock() - amount < car.getMinStock()){
                return 0;
            }
            car.setStock(car.getStock() - amount);
            carDistributionRepository.save(car);
            return car.getStock();
        }
        return -1;
    }
    public boolean save (NewCarDto newCarDto) {
        carDistributionRepository.save(fromNewDto(newCarDto));
        return true;
    }
    public Car fromNewDto (NewCarDto newCarDto){
        Car car = new Car();
        car.setBrand(newCarDto.brand().toLowerCase());
        car.setModel(newCarDto.model().toLowerCase());
        car.setColour(newCarDto.colour().toLowerCase());
        car.setType(newCarDto.type().toLowerCase());
        car.setStock(newCarDto.stock());
        car.setMinStock(newCarDto.minStock());
        car.setMaxStock(newCarDto.maxStock());
        return car;
    }
    public Car getCarByName(String carName){
        return carDistributionRepository.getReferenceById(carName);
    }

    public List<Car> getCars(){
        return carDistributionRepository.findAll();
    }
    public String getCarsString(){
        return carDistributionRepository.findAll().toString();
    }
}
