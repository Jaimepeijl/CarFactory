package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.LaptopDistributionService;
import nl.ordina.distribution.repository.dto.LaptopDto;
import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<Laptop> getLaptops(){
        return laptopDistributionService.getLaptops();
    }


    @PutMapping("/laptops/update-stock/")
    public ResponseEntity<Object> updateStock (@RequestBody @Valid LaptopDto laptopDto){
        int stockCode = laptopDistributionService.updateStock(laptopDto);
        if (stockCode > 0){
            return new ResponseEntity<>((stockCode),
                    HttpStatus.OK);
        } else if(stockCode < 0) {
            return new ResponseEntity<>("Did not find phone '" + laptopDto.model() + "'",
                    HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Not enough stock for " + laptopDto.model(),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
