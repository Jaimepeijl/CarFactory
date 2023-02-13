package model;

import java.util.ArrayList;

public class Car {
    private String brand;
    private String model;
    private String colour;
    private String type;
    private int stock;
    private static ArrayList<Car>cars = new ArrayList<>();

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

    public static ArrayList<Car> getCars() {
        return cars;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getType() {
        return type;
    }

    public int getStock() {
        return stock;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", type='" + type + '\'' +
                ", stock=" + stock +
                '}';
    }
}