package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.NewCarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/cars/new")
    public ResponseEntity<Object> newPhone (@RequestBody @Valid NewCarDto newCarDto){
        this.carDistributionService.save(newCarDto);
        if (newCarDto.stock() == 1){
            return new ResponseEntity<>("Successfully added " + newCarDto.stock() + " " + newCarDto.brand()  + " " + newCarDto.model() + " car", HttpStatus.OK);
        }
        return new ResponseEntity<>("Successfully added " + newCarDto.stock() + " " + newCarDto.brand() + " " + newCarDto.model() + " cars", HttpStatus.OK);
    }

    @GetMapping("/cars")
    public String getCars(){
        return carDistributionService.getCarsString();
    }

    @PutMapping("/cars/update-stock")
    public ResponseEntity<Object> updateStock (@RequestBody @Valid CarDto carDto){
        int stockCode = carDistributionService.updateStock(carDto);
        if (stockCode > 0) {
            return new ResponseEntity<>("Current stock for " + carDto.name() + " is now: " + stockCode, HttpStatus.OK);
        } else if (stockCode < 0) {
            return new ResponseEntity<>("Did not find car '" + carDto.name() + "'", HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(String.format("The current stock for %s reached it's minimum, " +
                            "please notify the procurement department", carDto.name()), HttpStatus.BAD_REQUEST);
                }
    }

}
