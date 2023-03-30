package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.LaptopDistributionService;
import nl.ordina.distribution.repository.dto.LaptopDto;
import nl.ordina.distribution.repository.dto.LaptopOrder;
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
            return new ResponseEntity<>("Did not find laptop", HttpStatus.BAD_REQUEST);
        } else {
            int orderAmount = 5;
            LaptopOrder laptopOrder = new LaptopOrder(orderAmount);
            if (factoryOrder(laptopOrder) != null) {
                return new ResponseEntity<>(String.format("The current stock for %s reached it's minimum, " + orderAmount +
                        " cars are ordered in the factory", laptopDto.model()), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Object> factoryOrder(LaptopOrder laptopOrder) {
        final String URL = "http://localhost:8082/order/laptops";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LaptopOrder> requestEntity = new HttpEntity<>(laptopOrder, headers);

        ResponseEntity<Object> response = restTemplate
                .exchange(URL, HttpMethod.POST,
                        requestEntity, Object.class);
        System.out.println(response.getBody());
        return response;
    }
}
