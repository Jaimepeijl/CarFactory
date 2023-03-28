package nl.ordina.distribution.test;

import nl.ordina.distribution.domain.CarDistributionService;
import nl.ordina.distribution.repository.dto.CarDto;
import nl.ordina.distribution.repository.dto.CarOrder;
import nl.ordina.distribution.repository.dto.OrderCarsResponse;
import nl.ordina.distribution.repository.model.Car;
import nl.ordina.distribution.repository.repository.CarDistributionRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
class CarDistributionServiceTest {

    @Mock
    private CarDistributionRepository carDistributionRepository;

    private CarDistributionService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        carService = new CarDistributionService(carDistributionRepository);
    }

    @Test
    void updateStock() {
        // Given
        UUID uuid = UUID.randomUUID();
        CarDto carDto = new CarDto("Tesla", 2, "Metalic grijs", uuid);
        Car car = new Car(uuid, "Tesla", "Model S3XY", "Metalic grijs", "Elektrisch", 220,380,500,4,2,4);

        // When
        if (carDto.uuid() != null){
            when(carDistributionRepository.findCarById(carDto.uuid())).thenReturn(car);
        } else {
            when(carDistributionRepository.findCarByBrandEqualsIgnoreCaseAndColourEqualsIgnoreCase(carDto.name(), carDto.colour())).thenReturn(car);
        }
        int result = carService.updateStock(carDto);

        // Then
        assertEquals(2, result);
        verify(carDistributionRepository, times(1)).save(car);
    }
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CarOrder carOrder;

    @InjectMocks
    private CarDistributionController carDistributionController;

    @Test
    void testFactoryOrder() {
//        Given
        OrderCarsResponse expectedResponse = new OrderCarsResponse();
        ResponseEntity<OrderCarsResponse> mockResponse = ResponseEntity.ok(expectedResponse);

//        When
        Mockito.when(restTemplate.exchange(Mockito.anyString(),Mockito.eq(HttpMethod.POST),
                Mockito.<HttpEntity<CarOrder>>any(),Mockito.eq(OrderCarsResponse.class)))
                .thenReturn(mockResponse);

        OrderCarsResponse actualResponse = carDistributionController.factoryOrder(new CarOrder());
//        Then
    }


    @Test
    void getCarByName() {
        String carName = "Tesla";
        UUID uuid = UUID.randomUUID();
        Car car = new Car(uuid, "Tesla", "Model S3XY", "Metalic grijs", "Elektrisch", 220,380,500,4,2,4);

        when(carDistributionRepository.findCarByBrandEqualsIgnoreCase(carName)).thenReturn(car);
        Car result = carService.getCarByName(carName);

        assertEquals("Tesla", result.getBrand());
        assertEquals("Model S3XY", result.getModel());
        assertEquals(uuid, result.getId());
    }

    @Test
    void getCars() {
    }
}