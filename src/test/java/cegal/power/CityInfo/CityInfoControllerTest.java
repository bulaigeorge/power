package cegal.power.CityInfo;

import cegal.power.CityInfo.CityInfoDTOs.CityInfoMonthDTO;
import cegal.power.location.Location;
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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityInfoControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Value("${server.port}")
    String configPort;

    String baseUri = "http://localhost:";

    String[] months = new String[]{"01", "02", "03"};

    private CityInfo oslo = new CityInfo("Oslo", 9999, 14903, 23000, Arrays.asList(months));
    private CityInfo paris = new CityInfo("Paris", 9999, 14903, 23000, Arrays.asList(months));
    private CityInfo berlin = new CityInfo("Berlin", 9999, 14903, 23000, Arrays.asList(months));

    List<CityInfo> cities = new ArrayList<>();

    @Test
    @Order(1)
    void ShouldFindALlCities() {
        //arrange
        cities.add(oslo);
        cities.add(paris);
        //act
        ResponseEntity<CityInfo[]> response = restTemplate.getForEntity(baseUri + configPort + "/api/information", CityInfo[].class);
        //assert
        assertEquals(cities.size(), response.getBody().length);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(1)
    void ShouldFindCityByName() {
        //act
        ResponseEntity<CityInfo> response = restTemplate.getForEntity(baseUri + configPort + "/api/information/Berlin", CityInfo.class);
        //assert
        assertNotNull(response);
        assertEquals(berlin.getCity(), response.getBody().getCity());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(1)
    void findCityByNameAndMonth() {
        //arrange
        //act
        ResponseEntity<CityInfoMonthDTO> response = restTemplate.getForEntity(baseUri + configPort + "/api/information/Berlin/01", CityInfoMonthDTO.class);
        //assert
        assertNotNull(response);
        assertEquals(berlin.getCity(), response.getBody().city());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(2)
    void ShouldSaveCityInfo() {
        //act
        ResponseEntity<CityInfo> response = restTemplate.postForEntity(baseUri + configPort + "/api/information/Oslo", new HttpEntity<>(new HttpHeaders()), CityInfo.class);
        //assert
        assertEquals(oslo.getCity(), response.getBody().getCity());
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    @Order(2)
    void ShouldUpdateCityInfo() {
        //act
        ResponseEntity<CityInfo> response = restTemplate.exchange(baseUri + configPort + "/api/information/Paris", HttpMethod.PUT, new HttpEntity<>(paris), CityInfo.class);
        //assert
        assertEquals(paris.getCity(), response.getBody().getCity());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(3)
    void deleteCityInfoByName() {
        //act
        ResponseEntity<CityInfo> response = restTemplate.exchange(baseUri + configPort + "/api/location/Oslo", HttpMethod.DELETE, new HttpEntity<>(new HttpHeaders()), CityInfo.class);
        //assert
        assertEquals(204, response.getStatusCode().value());
    }
}