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
import org.springframework.http.HttpMethod;


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
        Emission[] mockResponse = restTemplate.getForEntity(baseUri + configPort + "/api/emission", Emission[].class).getBody();
        //assert
        assertEquals(emissions.size(), mockResponse.length);
    }

    @Test
    @Order(1)
    void ShouldFindEmissionByType() {
        //arrange
        //act
        Emission mockResponse = restTemplate.getForEntity(baseUri + configPort + "/api/emission/hydro", Emission.class).getBody();
        //assert
        assertNotNull(mockResponse);
        assertEquals(hydro.getType(), mockResponse.getType());
    }

    @Test
    @Order(2)
    void ShouldSaveEmission() {
        //arrange
        //act
        Emission response = restTemplate.postForEntity(baseUri + configPort + "/api/emission", coal, Emission.class).getBody();
        //assert
        assertEquals(coal.getEmission(), response.getEmission());
    }

    @Test
    @Order(2)
    void saveAllEmissions() {
        //arrange
        //act
        Emission[] mockResponse = restTemplate.postForEntity(baseUri + configPort + "/api/emission/all", emissions, Emission[].class).getBody();
        //assert
        assertEquals(emissions.size(), mockResponse.length);
    }

    @Test
    @Order(3)
    void ShouldUpdateEmission() {
        //arrange
        //act
        Emission response = restTemplate.exchange(baseUri + configPort + "/api/emission", HttpMethod.PUT, new HttpEntity<>(wind), Emission.class).getBody();
        //assert
        assertEquals(wind.getEmission(), response.getEmission());
    }

    /*@Test
    void deleteEmission() {
        //arrange
        //act
        restTemplate.delete(baseUri + configPort + "/api/emission/nuclear");
        //assert

    }*/

}