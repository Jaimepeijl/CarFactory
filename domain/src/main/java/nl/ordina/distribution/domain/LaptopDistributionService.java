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

    // Check stock
    // Order
    // Update


    public boolean checkStock(LaptopDto laptopDto) {
        int orderAmount = laptopDto.orderAmount();
        return orderAmount >= laptopDistributionRepository
                .findLaptopByModelEqualsIgnoreCase(laptopDto.model()).getStock();
    }


    public boolean checkColour(LaptopDto laptopDto) {
        if (laptopDto.colour() != null) {
            return true;
        }
        return false;
    }

    public int modifyStock(LaptopDto laptopDto) {
        int updatedStock;
        if (checkColour(laptopDto) && checkStock(laptopDto)) {
            updatedStock = laptopDistributionRepository.findLaptopByModelEqualsIgnoreCase
                    (laptopDto.model()).getStock();
        } else if (!checkColour(laptopDto) && checkStock(laptopDto)){
            updatedStock = laptopDistributionRepository.
                    findLaptopByModelEqualsIgnoreCaseAndColourEqualsIgnoreCase(laptopDto.model(), laptopDto.colour())
                    .getStock();
            return updatedStock;
        }
        return 0;
    }

    public boolean checkIfEnoughStock(LaptopDto laptopDto) {
        if (modifyStock(laptopDto) >= getLaptopByModel(laptopDto.model()).getMinStock()) {
            return true;
        } else {
            return false;
        }
    }



    public int updateStock(LaptopDto laptopDto) {
        int orderAmount = laptopDto.orderAmount();
        if (laptopDto.colour() != null) {
            Laptop laptop = laptopDistributionRepository.
                    findLaptopByModelEqualsIgnoreCaseAndColourEqualsIgnoreCase(laptopDto.model(), laptopDto.colour());
            if (laptop.getStock() - orderAmount < laptop.getMinStock()) {
                return 0;
            }
            laptop.setStock(laptop.getStock() - orderAmount);
            laptopDistributionRepository.save(laptop);
            return laptop.getStock();
        }
        if (laptopDistributionRepository.findLaptopByModelEqualsIgnoreCase(laptopDto.model()) != null) {
            Laptop laptop = getLaptopByModel(laptopDto.model());
            if (laptop.getStock() - orderAmount < laptop.getMinStock()) {
                return 0;
            }
            laptop.setStock(laptop.getStock() - orderAmount);
            laptopDistributionRepository.save(laptop);
            return laptop.getStock();
        }
        return -1;
    }

    public int processOrder(LaptopDto laptopDto) {
        int orderAmount = laptopDto.orderAmount();
        Laptop laptop = checkStock2(laptopDto, orderAmount);

        if (laptop != null) {
            laptop.setStock(laptop.getStock() - orderAmount);
            laptopDistributionRepository.save(laptop);
            return laptop.getStock();
        } else {
            return -1;
        }
    }

    private Laptop checkStock2(LaptopDto laptopDto, int orderAmount) {
        Laptop laptop;
        if (laptopDto.colour() != null) {
            laptop = laptopDistributionRepository.
                    findLaptopByModelEqualsIgnoreCaseAndColourEqualsIgnoreCase(laptopDto.model(), laptopDto.colour());
        } else {
            laptop = laptopDistributionRepository.findLaptopByModelEqualsIgnoreCase(laptopDto.model());
        }

        if (laptop != null) {
            if (laptop.getStock() - orderAmount < 0) {
                return null;
            } else  if (laptop.getStock() - orderAmount < laptop.getMinStock()) {
                // TODO: er is te weinig wel laptop object teruggeven + bijbestellen
                return laptop;
            } else {
                return laptop;
            }
        }
        return null;
    }

    public Laptop getLaptopByModel(String laptopModel) {
        return laptopDistributionRepository.findLaptopByModelEqualsIgnoreCase(laptopModel);
    }

    public List<Laptop> getLaptops() {
        return laptopDistributionRepository.findAll();
    }
}

