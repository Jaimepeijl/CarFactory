package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.PhoneDistributionService;
import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.model.Phone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PhoneDistributionController {

    public final PhoneDistributionService phoneDistributionService;

    public PhoneDistributionController(PhoneDistributionService phoneDistributionService) {
        this.phoneDistributionService = phoneDistributionService;
    }

    @GetMapping("/phones")
    public String getPhones() {
        return phoneDistributionService.getPhonesString();
    }

    @PutMapping("/phones/update-stock/{phoneName}")
    public ResponseEntity<Object> updateStock (@PathVariable String phoneName, @RequestBody PhoneDto phoneDto){
        try{
            int amount = phoneDto.getStock();
            if (amount <= 0 ){
                amount = 1;
            };
            if (phoneDistributionService.getPhoneByName(phoneDto.getName()) != null){
                Phone phone = phoneDistributionService.getPhoneByName(phoneDto.getName());
                if (phoneDistributionService.updateStock(phoneName, amount)){
                    return new ResponseEntity<>("Stock is now: " + (phone.getStock()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Not enough stock", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>("Phone doesn't exist", HttpStatus.BAD_REQUEST);
        } catch (CarNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
