package nl.ordina.distribution.repository.repository;

import nl.ordina.distribution.repository.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CarDistributionRepository extends JpaRepository <Car, String> {
    Car findCarByBrandEqualsIgnoreCase(String brand);
    Car findCarByBrandEqualsIgnoreCaseAndColourEqualsIgnoreCase(String name, String colour);
    Car findCarById(UUID id);
}
