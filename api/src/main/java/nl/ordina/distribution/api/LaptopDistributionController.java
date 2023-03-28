package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.LaptopDistributionService;
import nl.ordina.distribution.repository.dto.LaptopDto;
import nl.ordina.distribution.repository.dto.LaptopOrder;
import nl.ordina.distribution.repository.dto.OrderLaptopsResponse;
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
            return new ResponseEntity<>("Did not find phone '" + laptopDto.model() + "'", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Not enough stock for " + laptopDto.model(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("order/laptops")
    public void orderLaptops(@RequestBody LaptopDto laptopDto, int amount) {
        laptopDistributionService.updateStock(laptopDto);
    }

    private final String BASE_URL = "http://localhost:8082";
    private final String ORDER_LAPTOPS_ENDPOINT = "/order/laptops";

    RestTemplate restTemplate = new RestTemplate();

    public OrderLaptopsResponse factoryOrder(LaptopOrder laptopOrder) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LaptopOrder> requestEntity = new HttpEntity<>(laptopOrder, headers);

        ResponseEntity<OrderLaptopsResponse> response = restTemplate
                .exchange(BASE_URL + ORDER_LAPTOPS_ENDPOINT,
                        HttpMethod.POST, requestEntity, OrderLaptopsResponse.class);

        OrderLaptopsResponse orderLaptopsResponse = response.getBody();
        System.out.println(orderLaptopsResponse);

        return response.getBody();
    }

}
