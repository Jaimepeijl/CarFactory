package nl.ordina.distribution.api;
import java.io.Serial;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException (){
        super("De auto is niet gevonden");
    }
    public CarNotFoundException (String carName){
        super("Auto " + carName + " is niet gevonden");
    }
}
