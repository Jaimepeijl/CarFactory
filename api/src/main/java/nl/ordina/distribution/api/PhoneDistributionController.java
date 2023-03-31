package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.PhoneDistributionService;
import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.dto.PhoneOrder;
import nl.ordina.distribution.repository.model.Phone;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
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
            return new ResponseEntity<>((String.format("Your order of %d %s %s %s has been successfully processed." +
                            " Remaining available supply: %d", phoneDto.orderAmount(),
                    phoneDistributionService.getPhone(phoneDto).getName(),
                    phoneDistributionService.getPhone(phoneDto).getColour(),
                    phoneDistributionService.getPhone(phoneDto).getMemory(),
                    (stockCode - phoneDistributionService.getPhone(phoneDto).getMinStock()))), HttpStatus.OK);
        } else if (stockCode < 0) {
            return new ResponseEntity<>("Did not find phone", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(String.format("The current stock is insufficient for your request. " +
                            "The maximum order for this phone is %d",
                    (phoneDistributionService.getPhone(phoneDto).getStock() -
                            phoneDistributionService.getPhone(phoneDto).getMinStock())), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> factoryOrder(PhoneOrder phoneOrder) {
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8082/order/mobiles";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PhoneOrder> requestEntity = new HttpEntity<>(phoneOrder, headers);

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
}
