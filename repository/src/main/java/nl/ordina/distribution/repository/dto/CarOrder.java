package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;

public record CarOrder (@Min(1)int amountOfCars, Long factoryId){

}
