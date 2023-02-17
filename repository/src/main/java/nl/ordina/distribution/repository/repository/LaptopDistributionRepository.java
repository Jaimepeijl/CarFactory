package nl.ordina.distribution.repository.repository;

import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class LaptopDistributionRepository {

    public ArrayList <Laptop> getLaptops(){
        return Laptop.getLaptops();
    }

}
