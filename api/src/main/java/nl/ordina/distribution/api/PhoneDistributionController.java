package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.PhoneDistributionService;
import nl.ordina.distribution.repository.dto.PhoneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/phones")
public class PhoneDistributionController {

    public final PhoneDistributionService phoneDistributionService;

    public PhoneDistributionController(PhoneDistributionService phoneDistributionService) {
        this.phoneDistributionService = phoneDistributionService;
    }

    @GetMapping("/")
    public String getPhones() {
        return phoneDistributionService.getPhonesString();
    }

    @PutMapping("/update-stock")
    public ResponseEntity<Object> updateStock (@RequestBody PhoneDto phoneDto){
        phoneDistributionService.updateStock(phoneDto);
        if (phoneDistributionService.updateStock(phoneDto)){
            return new ResponseEntity<>("Stock is now: " + (phoneDto.stock()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not enough stock", HttpStatus.BAD_REQUEST);
        }
    }
}
