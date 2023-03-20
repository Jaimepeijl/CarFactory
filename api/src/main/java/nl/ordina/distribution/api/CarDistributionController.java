package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.CarOrder;
import nl.ordina.distribution.repository.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class CarDistributionController {
    public final CarDistributionService carDistributionService;
    public CarDistributionController(CarDistributionService carDistributionService) {
        this.carDistributionService = carDistributionService;
    }

    @GetMapping("/")
    protected String showHomeScreen() {
        return "homeScreen";
    }

    @GetMapping("/cars")
    public List<Car> getCars(){
        return carDistributionService.getCars();
    }

    @PutMapping("/cars/update-stock")
    public ResponseEntity<Object> updateStock (@RequestBody @Valid CarDto carDto){
        int stockCode = carDistributionService.updateStock(carDto);
        if (stockCode > 0) {
            return new ResponseEntity<>(stockCode, HttpStatus.OK);
        } else if (stockCode < 0) {
            return new ResponseEntity<>("Did not find car '" + carDto.name() + "'", HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(String.format("The current stock for %s reached it's minimum, " +
                            "please notify the procurement department", carDto.name()), HttpStatus.BAD_REQUEST);
                }
    }
//    @PostMapping("/order/cars")
//    public boolean OrderCarAtFactory (CarOrder carOrder){
//
//    }

}
