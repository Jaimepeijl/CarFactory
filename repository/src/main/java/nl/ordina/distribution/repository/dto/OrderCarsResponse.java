package nl.ordina.distribution.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.ordina.distribution.repository.model.Car;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCarsResponse {
    private String orderId;
    private List<Car> orderedCars;
    private Double totalPrice;
}
