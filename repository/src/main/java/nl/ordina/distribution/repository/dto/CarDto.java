package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record CarDto (@NotBlank String name, @Min(0) int stock, String colour){
}