package nl.ordina.carfactory.repository.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Phone {
    private String name;
    private String colour;
    private int cameras;
    private int stock;
    private static ArrayList<Phone> phones = new ArrayList<>();

    public Phone() {
    }

    public Phone(String name, String colour, int cameras, int stock) {
        this.name = name;
        this.colour = colour;
        this.cameras = cameras;
        this.stock = stock;
    }

    public static boolean addPhone (Phone phone){
        if (phone != null){
            phones.add(phone);
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<Phone> getPhones() {
        return phones;
    }
}
