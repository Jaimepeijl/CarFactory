package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record NewCarDto (@NotBlank String brand, @NotBlank String model, @NotBlank String colour, String type, @Min(0) int stock, @Min(0) int minStock, @Min(2) int maxStock) {
}
