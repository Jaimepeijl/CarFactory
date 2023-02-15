package nl.ordina.carfactory.resources;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    private String name;

    public CarDto(String name) {
        this.name = name;
    }
}
