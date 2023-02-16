package cegal.power.emission;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmissionRepositoryTest {

    @Autowired
    private JpaEmissionRepository jpaEmissionRepository;

    @Test
    @Order(2)
    void ShouldFindAllEmissions() {
        Emission emission = new Emission("wind", 12);
        Emission saved = jpaEmissionRepository.save(emission);
        List<Emission> emissionList = (List<Emission>) jpaEmissionRepository.findAll();

        assertEquals(4, emissionList.size());
    }

    @Test
    @Order(2)
    void ShouldFindEmissionByType() {
        Emission found = jpaEmissionRepository.getByType("hydro");

        assertEquals("hydro", found.getType());
    }

    @Test
    @Order(1)
    void shouldSaveEmission() {
        Emission emission = new Emission("hydro", 23);
        Emission saved = jpaEmissionRepository.save(emission);

        assertEquals(saved, emission);
    }

    @Test
    @Order(1)
    void ShouldSaveAllEmissions() {
        List<Emission> emissions = new ArrayList<>();
        emissions.add(new Emission("nuclear", 12));
        emissions.add(new Emission("coal", 820));
        List<Emission> emissionList = (List<Emission>) jpaEmissionRepository.saveAll(emissions);

        assertEquals(2, emissionList.size());
    }

    @Test
    void ShouldDeleteEmissionByType() {
        jpaEmissionRepository.deleteByType("nuclear");
        Emission found = jpaEmissionRepository.getByType("nuclear");

        assertNull(found);
    }
}