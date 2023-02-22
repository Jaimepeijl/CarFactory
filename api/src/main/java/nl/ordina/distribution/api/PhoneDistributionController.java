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

    @PutMapping("/phones/update-stock")
    public ResponseEntity<Object> updateStock (@RequestBody PhoneDto phoneDto){
        try{
            System.out.println(phoneDto.getName());
            System.out.println(phoneDto.getStock());
            if (phoneDto.getStock() <= 0 ){
                return new ResponseEntity<>("You need to buy at least one.. Adjust stock please.",
                        HttpStatus.BAD_REQUEST);
            }
            if (phoneDistributionService.getPhoneByName(phoneDto.getName()) != null){
                Phone phone = phoneDistributionService.getPhoneByName(phoneDto.getName());
                if (phoneDistributionService.updateStock(phoneDto)){
                    return new ResponseEntity<>("Stock for " + phone.getName() + "is now: " + (phone.getStock()),
                            HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Not enough stock for " + phone.getName(),
                            HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>("Phone doesn't exist", HttpStatus.BAD_REQUEST);
        } catch (CarNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
