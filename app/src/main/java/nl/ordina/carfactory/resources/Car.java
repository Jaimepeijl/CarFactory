package nl.ordina.carfactory.resources;

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
        Car toyota = new Car("Toyota", "Corolla", "Grey", "Hybrid", 20);
        Car ford = new Car("Ford", "Fiesta", "Rood", "Benzine", 43);
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
        return  "brand=" + brand + '\n' +
                ", model=" + model + '\n' +
                ", colour=" + colour + '\n' +
                ", type=" + type + '\n' +
                ", stock=" + stock;
    }

    public static ArrayList<Car> getCars() {
        return cars;
    }
}