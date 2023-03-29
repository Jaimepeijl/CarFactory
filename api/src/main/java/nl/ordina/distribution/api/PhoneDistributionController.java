package nl.ordina.distribution.api;

import lombok.Getter;
import nl.ordina.distribution.domain.PhoneDistributionService;
import nl.ordina.distribution.repository.dto.OrderRequest;
import nl.ordina.distribution.repository.dto.OrderResponse;
import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.model.Phone;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class PhoneDistributionController {

    public final PhoneDistributionService phoneDistributionService;

    public PhoneDistributionController(PhoneDistributionService phoneDistributionService) {
        this.phoneDistributionService = phoneDistributionService;
    }

    @GetMapping("/phones")
    public List<Phone> getPhones() {
        return phoneDistributionService.getPhones();
    }

    @PutMapping("/phones/update-stock")
    public ResponseEntity<Object> updateStock(@RequestBody @Valid PhoneDto phoneDto) {

        int stockCode = phoneDistributionService.updateStock(phoneDto);
        if (stockCode > 0) {
            return new ResponseEntity<>(stockCode,
                    HttpStatus.OK);
        } else if (stockCode < 0) {
            return new ResponseEntity<>("Did not find phone '" + phoneDto.name() + "'",
                    HttpStatus.BAD_REQUEST);
        } else {
            OrderRequest phoneOrderRequest = new OrderRequest(5);
            factoryOrder(phoneOrderRequest);
            return new ResponseEntity<>("Not enough stock for " + phoneDto.name() + ", " + phoneOrderRequest.amountOfProducts() + " are ordered in the factory",
                    HttpStatus.CREATED);
        }
    }

    private final String URL = "http://localhost:8082/order/mobiles";
    RestTemplate restTemplate = new RestTemplate();

    @Getter
    private static class PhoneOrderResponse{
        private List<OrderResponse> phones;

    }
    public PhoneOrderResponse factoryOrder(OrderRequest phoneOrderRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderRequest> requestEntity = new HttpEntity<>(phoneOrderRequest, headers);

        ResponseEntity<PhoneOrderResponse> response = restTemplate
                .postForEntity(URL, requestEntity, PhoneOrderResponse.class);
        PhoneOrderResponse phoneOrderResponse = response.getBody();
        System.out.println(phoneOrderResponse.phones);
        System.out.println(phoneOrderResponse.getPhones());

        return phoneOrderResponse;

    }
}
