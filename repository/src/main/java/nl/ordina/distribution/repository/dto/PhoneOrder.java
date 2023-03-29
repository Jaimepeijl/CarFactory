package nl.ordina.distribution.repository.dto;

import lombok.Getter;

import javax.validation.constraints.Min;

public record PhoneOrder(@Min(1)int amountOfMobiles){

}
