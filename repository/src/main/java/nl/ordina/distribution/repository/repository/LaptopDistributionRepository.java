package nl.ordina.distribution.repository.repository;

import nl.ordina.distribution.repository.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LaptopDistributionRepository extends JpaRepository <Laptop, String> {

    Laptop findLaptopByModelEqualsIgnoreCase(String model);

    Laptop findLaptopByModelEqualsIgnoreCaseAndColourEqualsIgnoreCase(String brand, String colour);
}
