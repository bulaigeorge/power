package cegal.power.emission;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class EmissionControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Value("${server.port}")
    String configPort;

    String baseUri = "http://localhost:";

    @Test
    void findAllEmissions() {
    }

    @Test
    void findEmission() {
        //arrange
        Emission emission = new Emission("mockedHydro", 23);
        //act
        ResponseEntity<Emission> exchange1 = restTemplate.getForEntity(baseUri + configPort + "/api/emission/hydro", Emission.class);
        Emission mock = exchange1.getBody();
        //assert
        assertEquals(emission.getType(), mock.getType());
    }

    @Test
    void saveEmission() {
    }

    @Test
    void saveAllEmissions() {
    }

    @Test
    void updateEmission() {
    }

    @Test
    void deleteEmission() {
    }

    /*ResponseEntity<Emission> exchange1 = restTemplate
                .exchange("http://localhost:" + configPort + "/api/jokes", HttpMethod.POST, new HttpEntity<Emission>(emission), Emission.class);*/
}