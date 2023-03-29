package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.LaptopDistributionService;
import nl.ordina.distribution.repository.dto.LaptopDto;
import nl.ordina.distribution.repository.dto.OrderRequest;
import nl.ordina.distribution.repository.dto.OrderResponse;
import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class LaptopDistributionController {
    public final LaptopDistributionService laptopDistributionService;

    public LaptopDistributionController(LaptopDistributionService laptopDistributionService) {
        this.laptopDistributionService = laptopDistributionService;
    }

    @GetMapping("/laptops")
    public List<Laptop> getLaptops() {
        return laptopDistributionService.getLaptops();
    }


    @PutMapping("/laptops/update-stock/")
    public ResponseEntity<Object> updateStock(@RequestBody @Valid LaptopDto laptopDto) {
        int stockCode = laptopDistributionService.updateStock(laptopDto);
        if (stockCode > 0) {
            return new ResponseEntity<>((stockCode), HttpStatus.OK);
        } else if (stockCode < 0) {
            return new ResponseEntity<>("Did not find laptop '" + laptopDto.model() + "'", HttpStatus.BAD_REQUEST);
        } else {
            int orderAmount = 5;
            OrderRequest carOrderRequest = new OrderRequest(orderAmount);
            if (factoryOrder(carOrderRequest)) {
                return new ResponseEntity<>(String.format("The current stock for %s reached it's minimum, " + orderAmount +
                        " cars are ordered in the factory", laptopDto.model()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }


    public boolean factoryOrder(OrderRequest orderRequest) {
        final String BASE_URL = "http://localhost:8082";
        final String ORDER_LAPTOPS_ENDPOINT = "/order/laptops";

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
                .exchange(BASE_URL + ORDER_LAPTOPS_ENDPOINT, HttpMethod.POST,
                        requestEntity, OrderList.class);
        OrderList orderList = response.getBody();

        return true;
    }
}
