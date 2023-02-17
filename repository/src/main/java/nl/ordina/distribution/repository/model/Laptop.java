package nl.ordina.distribution.repository.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter@Setter
public class Laptop {
    private String brand;
    private String model;
    private String colour;
    private String type;
    private int stock;

    private static ArrayList<Laptop> laptops = new ArrayList<>();

    public Laptop(String brand, String model, String colour, String type, int stock) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.type = type;
        this.stock = stock;
    }

    public static void laptops() {
        Laptop macPro = new Laptop("Apple", "MacBook Pro", "White", "Extreme end", 0);
        Laptop dellOpt = new Laptop("Dell", "Optel", "Gray", "High end", 0);
        Laptop macAir = new Laptop("Apple", "Macbook Air", "Black", "Basic", 0);

        Laptop.addLaptops(macPro);
        Laptop.addLaptops(dellOpt);
        Laptop.addLaptops(macAir);
    }

    public static void addLaptops (Laptop laptop){
        if (laptop != null){
            laptops.add(laptop);
        }
    }

    public static ArrayList<Laptop> getLaptops(){
        return laptops;
    }
    @Override
    public String toString(){
        return String.format("Brand: %s\n, model: %s\n, colour: %s\n, type: %s\n, stock: %d\n",
                brand, model, colour, type, stock) + "--------------\n";
    }
}
