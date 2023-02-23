package nl.ordina.distribution.repository.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public @Data class Car {
    private String brand;
    private String model;
    private String colour;
    private String type;
    private int stock;
    private int minStock;
    private int maxStock;
    private static ArrayList<Car> cars = new ArrayList<>();

    public Car() {
    }

    public Car(String brand, String model, String colour, String type, int stock, int minStock, int maxStock) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.type = type;
        this.stock = stock;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    public static void cars (){
        Car tesla = new Car("Tesla", "Model 3SXY", "Black", "Electric",
                3, 2, 4);
        Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid",
                13, 1, 18);
        Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine",
                18, 4, 20);
        Car.addCar(tesla);
        Car.addCar(toyota);
        Car.addCar(ford);
    }
    public static boolean addCar (Car car){
        if (car != null){
            cars.add(car);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {

        return String.format("brand: %s,\nmodel: %s,\ncolour: %s\ntype: %s\n" +
                        "min. stock: %s\ncurrent stock: %d\nmax. stock: %d\n",
                brand, model, colour, type, minStock, stock, maxStock) + "--------------\n";
    }

    public static ArrayList<Car> getCars() {
        return cars;
    }
}