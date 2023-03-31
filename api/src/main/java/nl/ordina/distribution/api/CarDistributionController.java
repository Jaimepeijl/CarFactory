package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.CarOrder;
import nl.ordina.distribution.repository.model.Car;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
        Timer timer = new Timer();
        timer.schedule(new checkStock(), 0, 10000);
        return carDistributionService.getCars();
    }

    @PutMapping("/cars/update-stock")
    public ResponseEntity<Object> updateStock(@RequestBody @Valid CarDto carDto) {
        int stockCode = carDistributionService.updateStock(carDto);
        if (stockCode > 0) {
            return new ResponseEntity<>((String.format("Your order of %d %s %s %s has been successfully processed." +
                            " Remaining available supply: %d", carDto.orderAmount(),
                    carDistributionService.getCar(carDto).getBrand(),
                    carDistributionService.getCar(carDto).getModel(),
                    carDistributionService.getCar(carDto).getColour(),
                    (stockCode - carDistributionService.getCar(carDto).getMinStock()))), HttpStatus.OK);
        } else if (stockCode < 0) {
            return new ResponseEntity<>("Did not find car", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(String.format("The current stock is insufficient for your request. " +
                            "The maximum order for this car is %d",
                    (carDistributionService.getCar(carDto).getStock() -
                            carDistributionService.getCar(carDto).getMinStock())), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> factoryOrder(CarOrder carOrder) {
        String URL = "http://localhost:8082/order/cars";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CarOrder> requestEntity = new HttpEntity<>(carOrder, headers);

        try {
            ResponseEntity<Object> response = restTemplate.exchange(
                    URL,
                    HttpMethod.POST,
                    requestEntity,
                    Object.class);
            System.out.println(response.getBody());
            System.out.println(response.getStatusCode());
            return response;
        } catch (HttpClientErrorException ex) {
            HttpStatus status = ex.getStatusCode();
            return new ResponseEntity<>(status);
        }
    }
    public class checkStock extends TimerTask {
        @Override
        public void run() {
            Car car = carDistributionService.checkStockMethod();
            System.out.println("Stock checked");
            if(car != null){
                CarOrder carOrder = new CarOrder(1);
                if(factoryOrder(carOrder).getStatusCode() == HttpStatus.OK){
                    car.setStock(car.getStock() + 1);
                    carDistributionService.saveCar(car);
                    System.out.println(car.getBrand() + " stock is updated to " + car.getStock());
                } else {
                    System.out.println("couldn't order stock at the factory for " + car.getBrand());
                }
            } else {
                System.out.println("All cars are fully stocked");
            }

        }
    }

}

