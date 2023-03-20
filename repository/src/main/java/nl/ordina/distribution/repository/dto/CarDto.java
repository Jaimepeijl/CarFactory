package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record CarDto (@NotBlank String name, @Min(0) int orderAmount, String colour, UUID uuid){
}