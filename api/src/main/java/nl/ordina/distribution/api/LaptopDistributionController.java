package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.LaptopDistributionService;
import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LaptopDistributionController {
    public final LaptopDistributionService laptopDistributionService;

    public LaptopDistributionController(LaptopDistributionService laptopDistributionService) {
        this.laptopDistributionService = laptopDistributionService;
    }

    @PutMapping("/laptops/update-stock/{laptopModel}/{amount}")
    public ResponseEntity<Object> updateStock (@PathVariable String laptopModel, @PathVariable int amount){
        try {
            System.out.println(amount);
            if (amount <= 0 ){
                amount = 1;
            }
            if (laptopDistributionService.getLaptopByModel(laptopModel) != null){
                Laptop laptop = laptopDistributionService.getLaptopByModel(laptopModel);
                if (laptopDistributionService.updateLaptopStock(laptopModel, amount)){
                    return new ResponseEntity<>("Current stock for " + laptop.getBrand() + " "
                            + laptop.getModel() +" is now: " + (laptop.getStock()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(String.format("The current stock for %s %s reached it's minimum," +
                            " please notify the procurement department",
                            laptop.getBrand(), laptop.getModel()), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>("Laptop doesn't exist or is not available at Ordina",
                    HttpStatus.BAD_REQUEST);
        } catch (LaptopNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/laptops")
    public String getLaptops(){
        return laptopDistributionService.getLaptopsString();
    }

}
