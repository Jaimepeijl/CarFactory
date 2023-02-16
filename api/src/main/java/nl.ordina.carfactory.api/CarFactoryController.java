package nl.ordina.carfactory.api;

import nl.ordina.carfactory.domain.CarFactoryService;
import nl.ordina.carfactory.repository.model.Car;
import nl.ordina.carfactory.repository.model.CarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CarFactoryController {

    public final CarFactoryService carFactoryService;

    public CarFactoryController(CarFactoryService carFactoryService) {
        this.carFactoryService = carFactoryService;
    }

    @GetMapping("/")
    protected String showHomeScreen(Model model) {
        return "homeScreen";
    }

    @GetMapping("/cars")
    public String getCars(Model model){
//        Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 5);
//        Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 2);
//        Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 4);
//
//        model.addAttribute("Tesla", tesla);
//        model.addAttribute("Toyota", toyota);
//        model.addAttribute("Ford", ford);
//
//        model.addAttribute("cars", Car.getCars());


        return carFactoryService.getCars().toString();
    }
    @PatchMapping("/update-stock/{carName}")
    public ResponseEntity<Object> updateStock (@PathVariable String carName, @RequestBody CarDto carDto){
        try{
            int amount = 1;
                if (carFactoryService.getCarByName(carName) != null){
                    Car car = carFactoryService.getCarByName(carName);

                if(carFactoryService.updateStock(carName, carDto, amount)){
                    carFactoryService.updateStock(carName, carDto, amount);
                    return new ResponseEntity<>("Stock is now: " + (car.getStock()), HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Not enough stock", HttpStatus.BAD_REQUEST);
                }
        }
        return new ResponseEntity<>("Car doesn't exist", HttpStatus.BAD_REQUEST);
        } catch (CarNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tesla")
    public String getTesla(Model model){
        return "tesla";
    }
}
