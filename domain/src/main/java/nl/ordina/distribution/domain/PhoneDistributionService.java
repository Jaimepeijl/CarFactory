package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.NewPhoneDto;
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
        int amount = phoneDto.stock();
        if (phoneDistributionRepository.findPhoneByNameEqualsIgnoreCase(phoneDto.name()) != null){
            Phone phone = getPhoneByName(phoneDto.name());
            if (phone.getStock() - amount < phone.getMinStock()){
                return 0;
            }
            phone.setStock(phone.getStock() - amount);
            phoneDistributionRepository.save(phone);
            return phone.getStock();
        }
        return -1;
    }

    public boolean save (NewPhoneDto newPhoneDto){
        phoneDistributionRepository.save(fromNewDto(newPhoneDto));
        return true;
    }
    public Phone getPhoneByName(String phoneName){
        return phoneDistributionRepository.findPhoneByNameEqualsIgnoreCase(phoneName);
    }

    public Phone fromDto (PhoneDto phoneDto) {
        Phone phone = new Phone();
        phone.setName(phoneDto.name());
        phone.setStock(phone.getStock());
        return phone;
    }
    public Phone fromNewDto (NewPhoneDto newPhoneDto) {
        Phone phone = new Phone();
        phone.setName(newPhoneDto.name());
        phone.setColor(newPhoneDto.color());
        phone.setCameras(newPhoneDto.cameras());
        phone.setStock(newPhoneDto.stock());
        phone.setMinStock(newPhoneDto.minStock());
        phone.setMaxStock(newPhoneDto.maxStock());
        return phone;
    }

    public List<Phone> getPhones(){
        return phoneDistributionRepository.findAll();
    }
    public String getPhonesString(){
        return phoneDistributionRepository.findAll().toString();
    }
}
