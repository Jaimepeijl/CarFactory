package nl.ordina.distribution.domain;

import nl.ordina.distribution.repository.model.Laptop;
import nl.ordina.distribution.repository.repository.LaptopDistributionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class LaptopDistributionServiceTest {

    @Mock
    private LaptopDistributionRepository laptopDistributionRepository;

    private LaptopDistributionService laptopService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        laptopService = new LaptopDistributionService(laptopDistributionRepository);
    }

    @Test
    void updateStock() {
    }

    @Test
    void getLaptopByModel() {
        String laptopModel = "o";
        UUID uuid = UUID.randomUUID();
        Laptop laptop = new Laptop(uuid, "Macbook", "o", "white", "high", 1, 10, 20, 14, "i5");

        when(laptopDistributionRepository.findLaptopByModelEqualsIgnoreCase(laptopModel)).thenReturn(laptop);
        Laptop result = laptopService.getLaptopByModel(laptopModel);

        assertEquals(laptop, result);
    }

    @Test
    void getLaptopById() {
    }

    @Test
    void getLaptops() {
    }
}