package nl.ordina.carfactory.repository.model;

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
    private static ArrayList<Car> cars = new ArrayList<>();

    public Car() {
    }

    public Car(String brand, String model, String colour, String type, int stock) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.type = type;
        this.stock = stock;
    }
    public static void cars (){
        Car tesla = new Car("Tesla", "Model 3", "Black", "Electric", 10);
        Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 25);
        Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 45);
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

        return String.format("brand: %s,\nmodel: %s,\ncolour: %s\ntype: %s\nstock: %s\n",
                brand, model, colour, type,stock) + "--------------\n";
    }

    public static ArrayList<Car> getCars() {
        return cars;
    }
}