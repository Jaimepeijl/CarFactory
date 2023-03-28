package nl.ordina.distribution.repository.dto;

import javax.validation.constraints.Min;

public record OrderRequest(@Min(1)int amountOfProducts){

}
