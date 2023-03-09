package nl.ordina.distribution.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue
    private UUID id;
    private String brand;
    private String model;
    private String colour;
    private String type;
    private int maxSpeedInKmph;
    private int trunkContentInLiters;
    private int costsPerMonth;
    private int stock;
    private int minStock;
    private int maxStock;

}