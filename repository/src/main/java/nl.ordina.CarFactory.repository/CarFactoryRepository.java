package nl.ordina.CarFactory.repository;

import org.springframework.stereotype.Repository;

@Repository
public class CarFactoryRepository {

    public String getCars(){
        return "All cars";
    }
}
