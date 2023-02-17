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

    public static void phones(){
        Phone iPhone = new Phone("iPhone 123", "Grijs", 5, 10);
        Phone samsung = new Phone("Samsung 091", "Blauw", 3, 10);
        Phone sony = new Phone("Sony Space", "Zwart", 5, 10);
        Phone ordina = new Phone("Ordina Basic", "Groen", 1, 10);

        Phone.addPhone(iPhone);
        Phone.addPhone(samsung);
        Phone.addPhone(sony);
        Phone.addPhone(ordina);
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

    @Override
    public String toString() {
        return String.format("name: %s,\ncolour: %s,\ncameras: %s,\nstock: %s,\n", name, colour, cameras, stock) + "--------------\n";
    }
}
