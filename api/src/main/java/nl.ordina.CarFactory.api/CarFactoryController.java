package nl.ordina.CarFactory.api;

import nl.ordina.CarFactory.domain.CarFactoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarFactoryController {

    private final CarFactoryService carFactoryService;

    public CarFactoryController(CarFactoryService carFactoryService) {
        this.carFactoryService = carFactoryService;
    }
}
