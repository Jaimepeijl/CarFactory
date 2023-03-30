package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.CarOrder;
import nl.ordina.distribution.repository.model.Car;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public List<Car> getCars() {
        return carDistributionService.getCars();
    }

    @PutMapping("/cars/update-stock")
    public ResponseEntity<Object> updateStock(@RequestBody @Valid CarDto carDto) {
        int stockCode = carDistributionService.updateStock(carDto);
        if (stockCode > 0) {
            return new ResponseEntity<>(stockCode, HttpStatus.OK);
        } else if (stockCode < 0) {
            return new ResponseEntity<>("Did not find car", HttpStatus.BAD_REQUEST);
                } else {
            CarOrder carOrder = new CarOrder(1);
            if (factoryOrder(carOrder) != null){
                    return new ResponseEntity<>(String.format("The current stock for %s reached it's minimum, " + carOrder.amountOfCars() +
                            " cars are ordered in the factory", carDto.name()), HttpStatus.CREATED);
                }
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Object> factoryOrder(CarOrder carOrder){
        String URL = "http://localhost:8082/order/cars";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CarOrder> requestEntity = new HttpEntity<>(carOrder, headers);

        ResponseEntity<Object> response = restTemplate
                .exchange(URL, HttpMethod.POST,
                        requestEntity, Object.class);
        System.out.println(response.getBody());
        return response;
    }
}

