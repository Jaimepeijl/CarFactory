package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.model.Phone;
import nl.ordina.distribution.repository.repository.PhoneDistributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneDistributionService {

    public final PhoneDistributionRepository phoneDistributionRepository;

    public PhoneDistributionService(PhoneDistributionRepository phoneDistributionRepository) {
        this.phoneDistributionRepository = phoneDistributionRepository;
    }

    public boolean updateStock (PhoneDto phoneDto) {

        phoneDistributionRepository.save(fromDto(phoneDto));
        return true;
        }

    public Phone fromDto (PhoneDto phoneDto) {
        Phone phone = new Phone();
        phone.setName(phoneDto.name());
        phone.setStock(phoneDto.stock());
        return phone;
    }
    public PhoneDto toDto (Phone phone) {
        return new PhoneDto(phone.getName(), phone.getStock());
    }

    public List<Phone> getPhones(){
        return phoneDistributionRepository.findAll();
    }
    public String getPhonesString(){
        return phoneDistributionRepository.findAll().toString();
    }
}
