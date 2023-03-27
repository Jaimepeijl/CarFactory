package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.CarOrder;
import nl.ordina.distribution.repository.dto.OrderCarsResponse;
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
            int orderAmount = 5;
            CarOrder carOrder = new CarOrder(orderAmount);
            factoryOrder(carOrder);
                    return new ResponseEntity<>(String.format("The current stock for %s reached it's minimum, " + orderAmount +
                            "cars are ordered in the factory", carDto.name()), HttpStatus.BAD_REQUEST);

                }
    }

    private final String BASE_URL = "https://example.com/api";
    private final String ORDER_CARS_ENDPOINT = "/order/cars";

    private RestTemplate restTemplate;
    public OrderCarsResponse factoryOrder (CarOrder carOrder){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CarOrder> requestEntity = new HttpEntity<>(carOrder, headers);

        OrderCarsResponse response = restTemplate.exchange (BASE_URL + ORDER_CARS_ENDPOINT, HttpMethod.POST,
                requestEntity, OrderCarsResponse.class).getBody();

        return response;
    }
}

