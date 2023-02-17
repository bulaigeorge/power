package cegal.power.location;

import cegal.power.location.LocationDTOs.LocationDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocationControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Value("${server.port}")
    String configPort;

    String baseUri = "http://localhost:";

    private LocationDTO oslo = new LocationDTO("01", 2022, "Oslo", "hydro", 40, 120, "Akerselva");
    private LocationDTO paris = new LocationDTO("01", 2022, "Paris", "nuclear", 100, 3, "Mongolia");
    private LocationDTO berlin = new LocationDTO("01", 2022, "Berlin", "wind", 50, 70, "landbased");
    private LocationDTO stockholm = new LocationDTO("01", 2022, "Stockholm", "wind", 50, 70, "landbased");

    List<Location> locations = new ArrayList<>();

    @Test
    @Order(1)
    void ShouldFindAllLocations() {
        //arrange
        locations.add(new Location(oslo));
        locations.add(new Location(paris));
        locations.add(new Location(berlin));
        //act
        ResponseEntity<Location[]> response = restTemplate.getForEntity(baseUri + configPort + "/api/location", Location[].class);
        //assert
        assertEquals(locations.size(), response.getBody().length);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(1)
    void ShouldFindLocationById() {
        //act
        ResponseEntity<Location> response = restTemplate.getForEntity(baseUri + configPort + "/api/location/1", Location.class);
        //assert
        assertNotNull(response);
        assertEquals(oslo.city(), response.getBody().getCity());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(2)
    void ShouldSaveLocation() {
        //act
        ResponseEntity<Location> response = restTemplate.postForEntity(baseUri + configPort + "/api/location", stockholm, Location.class);
        //assert
        assertEquals(stockholm.city(), response.getBody().getCity());
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    @Order(2)
    void ShouldSaveAllLocations() {
        //assert
        List<LocationDTO> locationDTOS = new ArrayList<>();
        locationDTOS.add(oslo);
        locationDTOS.add(paris);
        locationDTOS.add(berlin);
        //act
        ResponseEntity<Location[]> response = restTemplate.postForEntity(baseUri + configPort + "/api/location/all", locationDTOS, Location[].class);
        //assert
        assertEquals(locationDTOS.size(), response.getBody().length);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    @Order(3)
    void ShouldUpdateLocation() {
        //act
        ResponseEntity<Location> response = restTemplate.exchange(baseUri + configPort + "/api/location/1", HttpMethod.PUT, new HttpEntity<>(oslo), Location.class);
        //assert
        assertEquals(oslo.city(), response.getBody().getCity());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(4)
    void ShouldReturn204ForDeleteLocation() {
        //act
        ResponseEntity<Location> response = restTemplate.exchange(baseUri + configPort + "/api/location/1", HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), Location.class);
        //assert
        assertEquals(204, response.getStatusCode().value());
    }
}