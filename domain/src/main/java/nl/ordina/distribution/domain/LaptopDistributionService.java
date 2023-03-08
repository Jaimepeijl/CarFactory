package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.LaptopDto;
import nl.ordina.distribution.repository.model.Laptop;
import nl.ordina.distribution.repository.repository.LaptopDistributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopDistributionService {
    private final LaptopDistributionRepository laptopDistributionRepository;

    public LaptopDistributionService(LaptopDistributionRepository laptopDistributionRepository) {
        this.laptopDistributionRepository = laptopDistributionRepository;
    }

    public int updateStock (LaptopDto laptopDto) {
        int amount = laptopDto.stock();
        if (laptopDistributionRepository.existsById(laptopDto.model().toLowerCase())){
            Laptop laptop = getLaptopByModel(laptopDto.model().toLowerCase());
            if (laptop.getStock() - amount < laptop.getMinStock()){
                return 0;
            }
            laptop.setStock(laptop.getStock() - amount);
            laptopDistributionRepository.save(laptop);
            return laptop.getStock();
        }
        return -1;
    }

    public Laptop getLaptopByModel(String laptopModel) {
        return laptopDistributionRepository.getReferenceById(laptopModel);
    }

    public List<Laptop> getLaptops(){
        return laptopDistributionRepository.findAll();
    }


    public String getLaptopsString() {
        return laptopDistributionRepository.findAll().toString();
    }
}

