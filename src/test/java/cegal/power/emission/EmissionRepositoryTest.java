package cegal.power.emission;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmissionRepositoryTest {

    @Autowired
    private JpaEmissionRepository jpaEmissionRepository;

    @Test
    void findAll() {
    }

    @Test
    void findByType() {
        Emission found = jpaEmissionRepository.getByType("hydro");

        assertNotNull(found);
    }

    @Test
    @Order(1)
    void shouldStoreEmission() {
        Emission emission = new Emission("hydro", 23);
        Emission saved = jpaEmissionRepository.save(emission);

        assertEquals(emission, saved);
    }

    @Test
    void saveAll() {
    }

    @Test
    void deleteByType() {

    }
}