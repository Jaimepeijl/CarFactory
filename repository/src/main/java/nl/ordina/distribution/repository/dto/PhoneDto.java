package nl.ordina.distribution.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto {
    private String name;
    private int stock;

    public PhoneDto() {
    }

    public PhoneDto(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }
}
