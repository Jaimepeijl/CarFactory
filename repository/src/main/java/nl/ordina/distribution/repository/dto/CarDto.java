package nl.ordina.distribution.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDto {
    private String name;
    private int stock;

    public CarDto() {
    }

    public CarDto(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }
}