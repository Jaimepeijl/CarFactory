package nl.ordina.distribution.repository.dto;

import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record LaptopDto(@NotBlank String model, @NotNull @Min(0) Integer stock, @NotNull @Min(0) Integer minStock,
                        @NotNull Integer maxStock) {
}
