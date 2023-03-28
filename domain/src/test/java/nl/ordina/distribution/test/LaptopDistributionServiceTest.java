package nl.ordina.distribution.test;

import nl.ordina.distribution.domain.LaptopDistributionService;
import nl.ordina.distribution.repository.dto.LaptopDto;
import nl.ordina.distribution.repository.model.Laptop;
import nl.ordina.distribution.repository.repository.LaptopDistributionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LaptopDistributionServiceTest {

    @Mock
    private LaptopDistributionRepository laptopDistributionRepository;
    @Mock
    private LaptopDistributionService laptopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        laptopService = new LaptopDistributionService(laptopDistributionRepository);
    }

    @Test
    void testGetLaptopByModel() {
        // Arrange
        ArrayList<Laptop> laptopList = new ArrayList<>();
        Laptop laptop1 = new Laptop(UUID.randomUUID(), "Macbook", "O", "white", "high",
                1, 3, 5, 3, "i7");
        Laptop laptop2 = new Laptop(UUID.randomUUID(), "Macbook", "X", "white", "high",
                1, 3, 5, 3, "i7");
        when(laptopDistributionRepository.findLaptopByModelEqualsIgnoreCase(laptop2.getModel())).thenReturn(laptop2);
        laptopList.add(laptop1);
        laptopList.add(laptop2);
        // Activate
        Laptop result = laptopService.getLaptopByModel("X");
        // Assert
        Assertions.assertEquals(laptop2, result);
    }

    @Test
    void updateStock() {
        // Given
        UUID uuid = UUID.randomUUID();
        LaptopDto laptopDto = new LaptopDto("X", 1, "White", "Macbook", uuid);
        Laptop laptop = new Laptop(UUID.randomUUID(), "Macbook", "O", "white", "high",
                5, 3, 5, 3, "i7");

        // When
        if (laptopDto.uuid() != null) {
            when(laptopDistributionRepository.findLaptopById(laptopDto.uuid())).thenReturn(laptop);
        } else {
            when(laptopDistributionRepository.findLaptopByModelEqualsIgnoreCaseAndColourEqualsIgnoreCase
                    (laptopDto.model(), laptopDto.colour())).thenReturn(laptop);
        }
        int result = laptopService.updateStock(laptopDto);

        // Then
        assertEquals(4, result);
        verify(laptopDistributionRepository, times(1)).save(laptop);
    }


}
