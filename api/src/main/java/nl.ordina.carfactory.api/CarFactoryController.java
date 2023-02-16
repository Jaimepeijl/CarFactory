package nl.ordina.carfactory.api;

import nl.ordina.carfactory.domain.CarFactoryService;
import nl.ordina.carfactory.repository.model.Car;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @PatchMapping("/update-stock")
    public String updateStock (@RequestBody String carName){
        int amount = 1;
        if (carName != null
//                && amount > 0
        ){
            Car car = carFactoryService.getCarByName(carName);
            if(carFactoryService.updateStock(car, amount)){
                carFactoryService.updateStock(car, amount);
                return "Stock is now: " + (car.getStock());
            }else {
                return "Not enough stock";
            }
        } else {
            return "Wrong input";
        }
    }

    @GetMapping("/tesla")
    public String getTesla(Model model){
        return "tesla";
    }
}
