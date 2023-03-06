package nl.ordina.distribution.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    private String brand;
    private String model;
    private String colour;
    private String type;
    private int stock;
    private int minStock;
    private int maxStock;

    @Override
    public String toString() {

        return String.format("brand: %s,\nmodel: %s,\ncolour: %s\ntype: %s\n" +
                        "min. stock: %s\ncurrent stock: %d\nmax. stock: %d\n",
                brand, model, colour, type, minStock, stock, maxStock) + "--------------\n";
    }
}