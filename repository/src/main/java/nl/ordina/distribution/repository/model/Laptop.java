package nl.ordina.distribution.repository.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.ArrayList;
@Getter@Setter
@Entity
public class Laptop {
    @NonNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String colour;
    @NotNull
    private String type;
    @NotNull
    @Min(0)
    private int stock;
    @NotNull
    @Min(4)
    private int minStock;
    @NotNull
    private int maxStock;


    private static ArrayList<Laptop> laptops = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long laptopId;

    public Laptop(String brand, String model, String colour, String type, int stock) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.type = type;
        this.stock = stock;
    }

    public Laptop() {

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

    public void setLaptopId(Long laptopId) {
        this.laptopId = laptopId;
    }

    public Long getLaptopId() {
        return laptopId;
    }
}
