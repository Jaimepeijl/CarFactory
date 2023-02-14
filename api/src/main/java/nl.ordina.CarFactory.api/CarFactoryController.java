package nl.ordina.CarFactory.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import nl.ordina.CarFactory.domain.CarFactoryService;
import org.springframework.web.bind.annotation.GetMapping;


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
//        model.addAttribute(carFactoryService.getCars());
        return "carOverview";
    }

    @GetMapping("/tesla")
    public String getTesla(){
        return carFactoryService.getTesla();
    }
}
