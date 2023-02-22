package nl.ordina.distribution.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaptopDto {
    private String model;
    private int stock;

    public LaptopDto() {
    }

    public LaptopDto(String model, int stock) {
        this.model = model;
        this.stock = stock;
    }
}
