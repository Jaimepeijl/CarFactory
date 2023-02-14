package nl.ordina.app.controller;

import nl.ordina.app.model.Car;
import org.springframework.stereotype.Controller;
import nl.ordina.app.service.CarFactoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;


@Controller
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
        Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 5);
        Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 2);
        Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 4);

        System.out.println(tesla);
        model.addAttribute("Tesla", tesla);
        return "carOverview";
    }

    @GetMapping("/tesla")
    public String getTesla(Model model){
        return "tesla";
    }
}
