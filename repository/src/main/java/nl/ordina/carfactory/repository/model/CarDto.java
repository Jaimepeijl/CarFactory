package nl.ordina.carfactory.repository.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    private String name;

    public CarDto() {
    }

    public CarDto(String name) {
        this.name = name;
    }
}