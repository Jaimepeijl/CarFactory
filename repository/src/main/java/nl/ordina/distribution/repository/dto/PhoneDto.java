package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record PhoneDto (@NotBlank String name, @NotNull @Min(0) int orderAmount, String colour, UUID uuid) {
}
