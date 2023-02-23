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

    public boolean updateStock (PhoneDto phoneDto) {
        phoneDistributionRepository.save(fromDto(phoneDto));
        return true;
    }

    public boolean save (NewPhoneDto newPhoneDto){
        phoneDistributionRepository.save(fromNewDto(newPhoneDto));
        return true;
    }

    public Phone fromDto (PhoneDto phoneDto) {
        Phone phone = new Phone();
        phone.setName(phoneDto.name());
        phone.setStock(phoneDto.stock());
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
