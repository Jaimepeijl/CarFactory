package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.PhoneDto;
import nl.ordina.distribution.repository.model.Phone;
import nl.ordina.distribution.repository.repository.PhoneDistributionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class PhoneDistributionService {
    private final PhoneDistributionRepository phoneDistributionRepository;

    public PhoneDistributionService(PhoneDistributionRepository phoneDistributionRepository) {
        this.phoneDistributionRepository = phoneDistributionRepository;
    }

    public int updateStock (PhoneDto phoneDto) {
        int orderAmount = phoneDto.orderAmount();
        Phone phone = getPhone(phoneDto);
            if (phone == null){
                return -1;
            }
            if (phone.getStock() - orderAmount < phone.getMinStock()){
                return 0;
            }
            phone.setStock(phone.getStock() - orderAmount);
            phoneDistributionRepository.save(phone);
            return phone.getStock();
        }
    public Phone getPhone (PhoneDto phoneDto){
        if (phoneDto.uuid() != null){
            return getPhoneById(phoneDto.uuid());
        } else if (phoneDto.colour() != null) {
            return getPhoneByNameOrColour(phoneDto.name(), phoneDto.colour());
        } else {
            return getPhoneByName(phoneDto.name());
        }
    }
    public Phone checkStockMethod() {
        List<Phone> phones = getPhones();
        for (int i = 0; i < phones.size(); ) {
            Phone phone = getPhoneById(phones.get(i).getId());
            if (phone.getStock() < phone.getMaxStock()) {
                return phone;
            }
            i++;
        }
        return null;
    }

    public Phone getPhoneByName(String phoneName){
        return phoneDistributionRepository.findPhoneByNameEqualsIgnoreCase(phoneName);
    }
    public Phone getPhoneByNameOrColour(String phoneName, String phoneColour){
        return phoneDistributionRepository.findPhoneByNameEqualsIgnoreCaseAndColourEqualsIgnoreCase(phoneName, phoneColour);
    }

    public Phone getPhoneById(UUID id){
        return phoneDistributionRepository.findPhoneById(id);
    }

    public List<Phone> getPhones(){
        return phoneDistributionRepository.findAll();
    }
    public void savePhone(Phone phone) {
        phoneDistributionRepository.save(phone);
    }
}
