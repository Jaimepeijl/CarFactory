package nl.ordina.CarFactory.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("nl.ordina.CarFactory.*")
public class CarFactoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarFactoryApplication.class, args);
    }
}
