package nl.ordina.distribution.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Laptop {
    private String brand;
    @Id
    private String model;
    private String colour;
    private String type;
    private int stock;
    private int minStock;
    private int maxStock;
    private int screenInInches;
    private String processor;

    private static ArrayList<Laptop> laptops = new ArrayList<>();

}
