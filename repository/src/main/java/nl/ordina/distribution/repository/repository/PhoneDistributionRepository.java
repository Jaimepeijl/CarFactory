package nl.ordina.distribution.repository.repository;

import nl.ordina.distribution.repository.model.Phone;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class PhoneDistributionRepository {
    public ArrayList<Phone> getPhones(){
        return Phone.getPhones();
    }
}
