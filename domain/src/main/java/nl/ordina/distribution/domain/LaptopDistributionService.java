package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.dto.LaptopDto;
import nl.ordina.distribution.repository.model.Laptop;
import nl.ordina.distribution.repository.repository.LaptopDistributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LaptopDistributionService {
    private final LaptopDistributionRepository laptopDistributionRepository;

    public LaptopDistributionService(LaptopDistributionRepository laptopDistributionRepository) {
        this.laptopDistributionRepository = laptopDistributionRepository;
    }

    public int updateStock (LaptopDto laptopDto) {
        int orderAmount = laptopDto.orderAmount();
        Laptop laptop = getLaptop(laptopDto);
            if (laptop == null){
                return -1;
            }
            if (laptop.getStock() - orderAmount < laptop.getMinStock()){
                return 0;
            }
            laptop.setStock(laptop.getStock() - orderAmount);
            laptopDistributionRepository.save(laptop);
            return laptop.getStock();
        }

    public Laptop getLaptop(LaptopDto laptopDto) {
        if (laptopDto.uuid() != null) {
            return getLaptopById(laptopDto.uuid());
        } else if (laptopDto.colour() != null) {
            return getLaptopByModelAndColour(laptopDto.model(), laptopDto.colour());
        } else {
            return getLaptopByModel(laptopDto.model());
        }
    }

    public Laptop getLaptopByModel(String laptopModel) {
        return laptopDistributionRepository.findLaptopByModelEqualsIgnoreCase(laptopModel);
    }
    public Laptop getLaptopById(UUID id){
        return laptopDistributionRepository.findLaptopById(id);
    }
    public Laptop getLaptopByModelAndColour(String laptopModel, String laptopColour){
        return laptopDistributionRepository.findLaptopByModelEqualsIgnoreCaseAndColourEqualsIgnoreCase(laptopModel, laptopColour);
    }

    public List<Laptop> getLaptops(){
        return laptopDistributionRepository.findAll();
    }
}

