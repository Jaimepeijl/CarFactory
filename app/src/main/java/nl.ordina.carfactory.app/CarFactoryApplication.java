package nl.ordina.carfactory.app;

import nl.ordina.carfactory.resources.Car;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
public class CarFactoryApplication {
    public static void main(String[] args) {
        Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 5);
        Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 2);
        Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 4);

        Car.addCar(tesla);
        Car.addCar(toyota);
        Car.addCar(ford);
        SpringApplication.run(CarFactoryApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }
}
