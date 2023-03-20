package nl.ordina.distribution.repository.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record LaptopDto (@NotBlank String model, @NotNull @Min(0) int orderAmount, String colour, String brand) {
}

