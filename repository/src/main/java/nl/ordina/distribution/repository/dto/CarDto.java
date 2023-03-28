package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;
import java.util.UUID;

public record CarDto (String name, @Min(0) int orderAmount, String colour, UUID uuid){
}