package nl.ordina.distribution.repository.model;

import lombok.AllArgsConstructor;
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
    private int minStock;
    private int maxStock;

    private static ArrayList<Laptop> laptops = new ArrayList<>();

    public Laptop(String brand, String model, String colour, String type, int stock, int minStock, int maxStock) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.type = type;
        this.stock = stock;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    public static void laptops() {
        Laptop macPro = new Laptop("Apple", "MacBookPro", "White", "Extreme end",
                38,20,50);
        Laptop dellOpt = new Laptop("Dell", "Optel", "Gray", "High end",
                44,30,60);
        Laptop macAir = new Laptop("Apple", "MacbookAir", "Black", "Basic",
                78, 76, 80);

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
