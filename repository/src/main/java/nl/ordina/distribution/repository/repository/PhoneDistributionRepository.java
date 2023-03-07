package nl.ordina.distribution.repository.repository;

import nl.ordina.distribution.repository.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneDistributionRepository extends JpaRepository<Phone, String> {
    Phone findPhoneByNameEqualsIgnoreCase(String name);
}
