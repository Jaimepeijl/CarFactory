package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.PhoneDistributionService;
import nl.ordina.distribution.repository.dto.NewPhoneDto;
import nl.ordina.distribution.repository.dto.PhoneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/phones/new")
    public ResponseEntity<Object> newPhone (@RequestBody @Valid NewPhoneDto newPhoneDto){
        this.phoneDistributionService.save(newPhoneDto);
        if (newPhoneDto.stock() == 1){
            return new ResponseEntity<>("Successfully added " + newPhoneDto.stock() + " " + newPhoneDto.name() + " phone", HttpStatus.OK);
        }
        return new ResponseEntity<>("Successfully added " + newPhoneDto.stock() + " " + newPhoneDto.name() + " phones", HttpStatus.OK);
    }

    @PutMapping("/phones/update-stock")
    public ResponseEntity<Object> updateStock (@RequestBody @Valid PhoneDto phoneDto){

        int stockCode = phoneDistributionService.updateStock(phoneDto);
        if (stockCode > 0){
                    return new ResponseEntity<>("Stock for " + phoneDto.name() + " is now: " + (stockCode),
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
