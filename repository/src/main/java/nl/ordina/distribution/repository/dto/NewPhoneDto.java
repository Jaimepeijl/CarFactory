package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record NewPhoneDto(@NotBlank String name, @NotBlank String color, int cameras, @Min(0) int stock, @Min(0) int minStock, @Min(2) int maxStock) {
}
