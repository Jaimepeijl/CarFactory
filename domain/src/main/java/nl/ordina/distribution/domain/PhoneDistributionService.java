package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.repository.PhoneDistributionRepository;
import org.springframework.stereotype.Service;

@Service
public class PhoneDistributionService {

    public final PhoneDistributionRepository phoneDistributionRepository;

    public PhoneDistributionService(PhoneDistributionRepository phoneDistributionRepository) {
        this.phoneDistributionRepository = phoneDistributionRepository;
    }

    public String getPhonesString(){
        return phoneDistributionRepository.getPhones().toString();
    }
}
