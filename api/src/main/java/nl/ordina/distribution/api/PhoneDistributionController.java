package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.PhoneDistributionService;
import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.model.Phone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> updateStock (@RequestBody @Valid PhoneDto phoneDto){

        int stockCode = phoneDistributionService.updateStock(phoneDto);
        if (stockCode > 0){
                    return new ResponseEntity<>(stockCode,
                            HttpStatus.OK);
                } else if(stockCode < 0) {
            return new ResponseEntity<>("Did not find phone '" + phoneDto.name() + "'",
                    HttpStatus.BAD_REQUEST);
        } else {
                return new ResponseEntity<>("Not enough stock for " + phoneDto.name(),
                        HttpStatus.BAD_REQUEST);
            }
        }


    }
