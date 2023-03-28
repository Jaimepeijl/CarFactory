package nl.ordina.distribution.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.ordina.distribution.repository.model.Laptop;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLaptopsResponse {
    private String orderId;
    private List<Laptop> orderedLaptops;
    private Double totalPrice;
}

