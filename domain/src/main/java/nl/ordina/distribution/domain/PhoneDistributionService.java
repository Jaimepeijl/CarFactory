package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.model.Phone;
import nl.ordina.distribution.repository.repository.PhoneDistributionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhoneDistributionService {
    private final PhoneDistributionRepository phoneDistributionRepository;

    public PhoneDistributionService(PhoneDistributionRepository phoneDistributionRepository) {
        this.phoneDistributionRepository = phoneDistributionRepository;
    }

    public int updateStock (PhoneDto phoneDto) {
        int orderAmount = phoneDto.orderAmount();
        if (phoneDto.colour() != null){
            Phone phone = phoneDistributionRepository.findPhoneByNameEqualsIgnoreCaseAndColourEqualsIgnoreCase(phoneDto.name(), phoneDto.colour());
            if (phone.getStock() - orderAmount < phone.getMinStock()){
                return 0;
            }
            phone.setStock(phone.getStock() - orderAmount);
            phoneDistributionRepository.save(phone);
            return phone.getStock();
        }
        if (phoneDistributionRepository.findPhoneByNameEqualsIgnoreCase(phoneDto.name()) != null){
            Phone phone = getPhoneByName(phoneDto.name());
            if (phone.getStock() - orderAmount < phone.getMinStock()){
                return 0;
            }
            phone.setStock(phone.getStock() - orderAmount);
            phoneDistributionRepository.save(phone);
            return phone.getStock();
        }
        return -1;
    }

    public Phone getPhoneByName(String phoneName){
        return phoneDistributionRepository.findPhoneByNameEqualsIgnoreCase(phoneName);
    }

    public Phone fromDto (PhoneDto phoneDto) {
        Phone phone = new Phone();
        phone.setName(phoneDto.name().toLowerCase());
        phone.setStock(phone.getStock());
        return phone;
    }

    public List<Phone> getPhones(){
        return phoneDistributionRepository.findAll();
    }
}
