package nl.ordina.app.model;

import lombok.Data;

import java.util.ArrayList;

public @Data class Car {
    private String brand;
    private String model;
    private String colour;
    private String type;
    private int stock;
    private static ArrayList<Car> cars = new ArrayList<>();

    public Car(String brand, String model, String colour, String type, int stock) {
        this.brand = brand;
        this.model = model;
        this.colour = colour;
        this.type = type;
        this.stock = stock;
    }
    public static boolean addCar (Car car){
        if (car != null){
            cars.add(car);
            return true;
        } else {
            return false;
        }
    }
    public static String printCars(){
        if (cars != null){
            return cars.toString();
        } else return "Error";
    }
    @Override
    public String toString() {
        return  "brand=" + brand + '\n' +
                ", model=" + model + '\n' +
                ", colour=" + colour + '\n' +
                ", type=" + type + '\n' +
                ", stock=" + stock;
    }
}