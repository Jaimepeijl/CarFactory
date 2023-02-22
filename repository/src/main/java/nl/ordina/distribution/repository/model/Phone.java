package nl.ordina.distribution.repository.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Getter
@Setter
@Entity
public class Phone {
    @Id
    private String name;
    private String colour;
    private int cameras;
    private int stock;
    private int minStock;
    private int maxStock;
    private static ArrayList<Phone> phones = new ArrayList<>();

    public Phone() {
    }

    public Phone(String name, String colour, int cameras, int stock, int minStock, int maxStock) {
        this.name = name;
        this.colour = colour;
        this.cameras = cameras;
        this.stock = stock;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    public static void addPhone (Phone phone){
        if (phone != null){
            phones.add(phone);
        }
    }

    public static ArrayList<Phone> getPhones() {
        return phones;
    }

    @Override
    public String toString() {
        return String.format("name: %s,\ncolour: %s,\ncameras: %s,\nstock: %s,\n", name, colour, cameras, stock) +
                "--------------\n";
    }
}
