package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.model.Phone;
import nl.ordina.distribution.repository.repository.PhoneDistributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PhoneDistributionService {

    public final PhoneDistributionRepository phoneDistributionRepository;

    public PhoneDistributionService(PhoneDistributionRepository phoneDistributionRepository) {
        this.phoneDistributionRepository = phoneDistributionRepository;
    }

    public boolean updateStock (PhoneDto phoneDto) {

        Phone phone = getPhoneByName(phoneDto.getName());
        int amount = phoneDto.getStock();

        if (phone.getStock() - amount >= phone.getMinStock()){
            phone.setStock(phone.getStock() - amount);
            return true;
        } return false;
    }
    public Phone getPhoneByName (String phoneName) {
        for (int i = 0; i < getPhones().size(); i++) {
            String phone = getPhones().get(i).getName().toLowerCase();
            if (Objects.equals(phoneName, phone)){
                return getPhones().get(i);
            }
        }
        return null;
    }

    public List<Phone> getPhones(){
        return phoneDistributionRepository.getPhones();
    }
    public String getPhonesString(){
        return phoneDistributionRepository.getPhones().toString();
    }
}
