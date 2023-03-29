package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.OrderRequest;
import nl.ordina.distribution.repository.dto.OrderResponse;
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
            OrderRequest carOrderRequest = new OrderRequest(orderAmount);
            if (factoryOrder(carOrderRequest)){
                    return new ResponseEntity<>(String.format("The current stock for %s reached it's minimum, " + orderAmount +
                            " cars are ordered in the factory", carDto.name()), HttpStatus.CREATED);
                }
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }

    public boolean factoryOrder(OrderRequest orderRequest){
        final String BASE_URL = "http://localhost:8082";
        final String ORDER_CARS_ENDPOINT = "/order/cars";

        RestTemplate restTemplate = new RestTemplate();
        class OrderList {
            private List<OrderResponse> list;

            public List<OrderResponse> getList() {
                return list;
            }

            public void setList(List<OrderResponse> list) {
                this.list = list;
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderRequest> requestEntity = new HttpEntity<>(orderRequest, headers);

        ResponseEntity<OrderList> response = restTemplate
                .exchange(BASE_URL + ORDER_CARS_ENDPOINT, HttpMethod.POST,
                        requestEntity, OrderList.class);
        OrderList orderList = response.getBody();
//        System.out.println(response);
//        System.out.println(orderList.list);
//        System.out.println(orderList.getList());
//        for (int i = 0; i < orderList.list.size(); i++) {
//            System.out.println(orderList.list.get(i).getId());
//            System.out.println(orderList.list.get(i).getPrice());
//        }
        return true;
    }
    }

