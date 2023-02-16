package nl.ordina.carfactory.api;
import java.io.Serial;

public class CarNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public CarNotFoundException (){
        super("De auto is niet gevonden");
    }
    public CarNotFoundException (String carName){
        super("Auto " + carName + " is niet gevonden");
    }
}
