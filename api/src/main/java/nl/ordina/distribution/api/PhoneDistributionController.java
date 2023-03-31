package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.PhoneDistributionService;
import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.dto.PhoneOrder;
import nl.ordina.distribution.repository.model.Phone;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
            return new ResponseEntity<>("Did not find phone",
                    HttpStatus.BAD_REQUEST);
        } else {
            PhoneOrder phoneOrder = new PhoneOrder(phoneDto.orderAmount());
            if (factoryOrder(phoneOrder) != null){
            return new ResponseEntity<>("Not enough stock for " + phoneDistributionService.getPhone(phoneDto).getName() + ", " + phoneOrder.amountOfMobiles() + " are ordered in the factory",
                    HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Unknown error", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> factoryOrder(PhoneOrder phoneOrder) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8082/order/mobiles";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PhoneOrder> requestEntity = new HttpEntity<>(phoneOrder, headers);


        ResponseEntity<Object> response = restTemplate
                .exchange(URL, HttpMethod.POST,
                        requestEntity, Object.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        return response;
    }
}
