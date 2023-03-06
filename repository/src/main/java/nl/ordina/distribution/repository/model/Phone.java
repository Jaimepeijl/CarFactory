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
public class Phone {
    @Id
    private String name;
    private String color;
    private int cameras;
    private int stock;
    private int minStock;
    private int maxStock;

    @Override
    public String toString() {
        return String.format("name: %s,\ncolour: %s,\ncameras: %s,\nstock: %s,\nminStock: %s, \nmaxStock: %s \n", name, color, cameras, stock, minStock, maxStock) +
                "--------------\n";
    }
}
