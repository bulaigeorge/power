package cegal.power.emission;


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
class EmissionControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Value("${server.port}")
    String configPort;

    String baseUri = "http://localhost:";
    Emission hydro = new Emission("hydro", 23);
    Emission nuclear = new Emission("nuclear", 12);
    Emission wind = new Emission("wind", 12);
    Emission coal = new Emission("coal", 820);
    List<Emission> emissions = new ArrayList<>();


    @Test
    @Order(1)
    void ShouldFindAllEmissions() {
        //arrange
        emissions.add(nuclear);
        emissions.add(wind);
        emissions.add(coal);
        //act
        ResponseEntity<Emission[]> response = restTemplate.getForEntity(baseUri + configPort + "/api/emission", Emission[].class);
        //assert
        assertEquals(emissions.size(), response.getBody().length);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(1)
    void ShouldFindEmissionByType() {
        //act
        ResponseEntity<Emission> response = restTemplate.getForEntity(baseUri + configPort + "/api/emission/hydro", Emission.class);
        //assert
        assertNotNull(response);
        assertEquals(hydro.getType(), response.getBody().getType());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(2)
    void ShouldSaveEmission() {
        //act
        ResponseEntity<Emission> response = restTemplate.postForEntity(baseUri + configPort + "/api/emission", coal, Emission.class);
        //assert
        assertEquals(coal.getEmission(), response.getBody().getEmission());
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    @Order(2)
    void saveAllEmissions() {
        //act
        ResponseEntity<Emission[]> response = restTemplate.postForEntity(baseUri + configPort + "/api/emission/all", emissions, Emission[].class);
        //assert
        assertEquals(emissions.size(), response.getBody().length);
        assertEquals(201, response.getStatusCode().value());
    }

    @Test
    @Order(3)
    void ShouldUpdateEmission() {
        //act
        ResponseEntity<Emission> response = restTemplate.exchange(baseUri + configPort + "/api/emission", HttpMethod.PUT, new HttpEntity<>(wind), Emission.class);
        //assert
        assertEquals(wind.getEmission(), response.getBody().getEmission());
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    @Order(4)
    void ShouldReturn204ForDeleteEmission() {
        //act
        ResponseEntity<Emission> response = restTemplate.exchange(baseUri + configPort + "/api/emission/nuclear", HttpMethod.DELETE, new HttpEntity<Emission>(new HttpHeaders()), Emission.class);
        //assert
        assertEquals(204, response.getStatusCode().value());
    }

}