package nl.ordina.distribution.repository.model;

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

    public static void phones(){
        Phone iPhone = new Phone("iPhone 123", "Grijs", 5, 300,100,500);
        Phone samsung = new Phone("Samsung 091", "Blauw", 3, 255, 250, 260);
        Phone sony = new Phone("Sony Space", "Zwart", 5, 800, 700, 1100);
        Phone ordina = new Phone("Ordina Basic", "Groen", 1, 1500, 1000, 2000);

        Phone.addPhone(iPhone);
        Phone.addPhone(samsung);
        Phone.addPhone(sony);
        Phone.addPhone(ordina);
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
