package nl.ordina.distribution.domain;
import nl.ordina.distribution.repository.model.Laptop;
import nl.ordina.distribution.repository.repository.LaptopDistributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LaptopDistributionService {
    public final LaptopDistributionRepository laptopDistributionRepository;

    public LaptopDistributionService(LaptopDistributionRepository laptopDistributionRepository) {
        this.laptopDistributionRepository = laptopDistributionRepository;
    }

    public String getLaptopsString() {
        return laptopDistributionRepository.getLaptops().toString();
    }

    public Laptop getLaptopByModel(String laptopModel) {
        for (int i = 0; i < getLaptops().size(); i++) {
            String laptop = getLaptops().get(i).getModel().toLowerCase();
            if (Objects.equals(laptopModel, laptop)) {
                return getLaptops().get(i);
            }
        }
        return null;
    }


    public boolean updateLaptopStock (String laptopModel, int amount){
        int minStock = 5;
        Laptop laptop = getLaptopByModel(laptopModel);
        if (laptop == getLaptopByModel("macbookpro")){
            minStock = 20;
        } else if (laptop == getLaptopByModel("optel")) {
            minStock = 30;
        } else if (laptop == getLaptopByModel("macbookair")) {
            minStock = 76;
        }
        if (laptop.getStock() - amount >= minStock){
            laptop.setStock(laptop.getStock() - amount);
            return true;
        } return false;
    }

    public List<Laptop> getLaptops(){
        return laptopDistributionRepository.getLaptops();
    }


}

