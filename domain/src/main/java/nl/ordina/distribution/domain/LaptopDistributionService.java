package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.repository.LaptopDistributionRepository;
import org.springframework.stereotype.Service;

@Service
public class LaptopDistributionService {
    public final LaptopDistributionRepository laptopDistributionRepository;

    public LaptopDistributionService(LaptopDistributionRepository laptopDistributionRepository) {
        this.laptopDistributionRepository = laptopDistributionRepository;
    }

    public String getLaptopsString(){
        return laptopDistributionRepository.getLaptops().toString();
    }
}
