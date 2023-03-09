package nl.ordina.distribution.repository.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record LaptopDto (@NotBlank String brand, @Min(0) int stock, String colour) {
}

