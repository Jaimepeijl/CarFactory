package nl.ordina.distribution.api;

public class LaptopNotFoundException extends RuntimeException {

    public LaptopNotFoundException() {
        super("De laptop is niet gevonden");
    }

    public LaptopNotFoundException(String laptopModel) {
        super("Laptop " + laptopModel + " is niet gevonden");
    }
}
