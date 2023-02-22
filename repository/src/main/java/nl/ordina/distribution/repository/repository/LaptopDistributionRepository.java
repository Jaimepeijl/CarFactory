package nl.ordina.distribution.repository.repository;

import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.ArrayList;

@Repository
public interface LaptopDistributionRepository extends JpaRepository <Laptop, Long> {
//    public default ArrayList <Laptop> getLaptops(){
//        return Laptop.getLaptops();
//    }

}
