package nl.ordina.distribution.test;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.domain.LaptopDistributionService;
import nl.ordina.distribution.repository.model.Laptop;
import nl.ordina.distribution.repository.repository.LaptopDistributionRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

class LaptopDistributionTest {

    @Mock
    private LaptopDistributionRepository laptopDistributionRepository;

    private LaptopDistributionService laptopDistributionService;

    @BeforeEach
    void setUp () {
        MockitoAnnotations.openMocks(this);
        laptopDistributionService = new LaptopDistributionService(laptopDistributionRepository);
    }

    @Test
    void getLaptopByModel () {
        Laptop laptop = new Laptop(UUID.randomUUID(), "Macbook", "O", "white", "high",  )
    }



}
