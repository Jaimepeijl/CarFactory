package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;

public record LaptopOrder(@Min(1) int amountOfLaptops) {
}
