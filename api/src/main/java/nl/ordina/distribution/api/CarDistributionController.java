package nl.ordina.distribution.api;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.model.Car;
import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
public class CarDistributionController {
    public final CarDistributionService carDistributionService;
    public CarDistributionController(CarDistributionService carDistributionService) {
        this.carDistributionService = carDistributionService;
    }

    @GetMapping("/")
    protected String showHomeScreen(Model model) {
        return "homeScreen";
    }

    @GetMapping("/cars")
    public String getCars(Model model){
//        model.addAttribute("Tesla", tesla);
//        model.addAttribute("Toyota", toyota);
//        model.addAttribute("Ford", ford);
//        model.addAttribute("cars", Car.getCars());
        return carDistributionService.getCarsString();
    }
    @PutMapping("/update-stock/{carName}/{amount}")
    public ResponseEntity<Object> updateStock (@PathVariable String carName, @PathVariable int amount){
        try{
            System.out.println(amount);
            if (amount <= 0 ){
                amount = 1;
            };
                if (carDistributionService.getCarByName(carName) != null){
                    Car car = carDistributionService.getCarByName(carName);
                if (carDistributionService.updateStock(carName, amount)){
                    return new ResponseEntity<>("Current stock for " + car.getBrand() + " " + car.getModel() +
                            " is now: " + (car.getStock()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(String.format("The current stock for %s %s reached it's minimum, " +
                            "please notify the procurement department", car.getBrand(), car.getModel()), HttpStatus.BAD_REQUEST);
                }
        }
        return new ResponseEntity<>("Car doesn't exist", HttpStatus.BAD_REQUEST);
        } catch (CarNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
