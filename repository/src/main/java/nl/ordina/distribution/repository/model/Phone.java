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
public class Phone {
    @Id
    private String name;
    private String color;
    private int cameras;
    private int stock;
    private int minStock;
    private int maxStock;
    private static ArrayList<Phone> phones = new ArrayList<>();

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
        return String.format("name: %s,\ncolour: %s,\ncameras: %s,\nstock: %s,\n", name, color, cameras, stock) +
                "--------------\n";
    }
}
