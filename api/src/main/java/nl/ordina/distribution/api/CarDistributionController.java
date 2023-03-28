package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.CarOrder;
import nl.ordina.distribution.repository.dto.OrderCarsResponse;
import nl.ordina.distribution.repository.model.Car;
import org.junit.jupiter.api.Assertions;
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
                            " cars are ordered in the factory", carDto.name()), HttpStatus.BAD_REQUEST);
                }
    }

    private final String BASE_URL = "http://localhost:8082";
    private final String ORDER_CARS_ENDPOINT = "/order/cars";

    RestTemplate restTemplate = new RestTemplate();
    private static class CarOrderResponse {
        private List<OrderCarsResponse> cars;

        public List<OrderCarsResponse> getCars() {
            return cars;
        }

        public void setCars(List<OrderCarsResponse> cars) {
            this.cars = cars;
        }
    }
    public List<OrderCarsResponse> factoryOrder (CarOrder carOrder){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CarOrder> requestEntity = new HttpEntity<>(carOrder, headers);

        ResponseEntity<CarOrderResponse> response = restTemplate
                .postForEntity(BASE_URL + ORDER_CARS_ENDPOINT,
                requestEntity, CarOrderResponse.class);
        CarOrderResponse carOrderResponse = response.getBody();
        System.out.println(response);
        System.out.println(carOrderResponse.cars);
        System.out.println(carOrderResponse.getCars());
        for (int i = 0; i < carOrderResponse.cars.size(); i++) {
            System.out.println(carOrderResponse.cars.get(i).getId());
            System.out.println(carOrderResponse.cars.get(i).getPrice());
        }
        return carOrderResponse.cars;
    }
}

