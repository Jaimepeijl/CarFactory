package nl.ordina.distribution.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Laptop {

    @Id
    @GeneratedValue
    private UUID laptopId;
    private String brand;
    private String model;
    private String colour;
    private String type;
    private int stock;
    private int minStock;
    private int maxStock;
    private int screenWidthInInches;
    private String processor;

}
