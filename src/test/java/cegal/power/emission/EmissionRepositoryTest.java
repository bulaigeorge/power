package cegal.power.emission;

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
        //arrange
        Emission emission = new Emission("wind", 12);
        //act
        Emission saved = jpaEmissionRepository.save(emission);
        List<Emission> emissionList = (List<Emission>) jpaEmissionRepository.findAll();
        //assert
        assertEquals(4, emissionList.size());
    }

    @Test
    @Order(2)
    void ShouldFindEmissionByType() {
        //act
        Emission found = jpaEmissionRepository.getByType("hydro");
        //assert
        assertEquals("hydro", found.getType());
    }

    @Test
    @Order(1)
    void shouldSaveEmission() {
        //arrange
        Emission emission = new Emission("hydro", 23);
        //act
        Emission saved = jpaEmissionRepository.save(emission);
        //assert
        assertEquals(saved, emission);
    }

    @Test
    @Order(1)
    void ShouldSaveAllEmissions() {
        //arrange
        List<Emission> emissions = new ArrayList<>();
        emissions.add(new Emission("nuclear", 12));
        emissions.add(new Emission("coal", 820));
        //act
        List<Emission> emissionList = (List<Emission>) jpaEmissionRepository.saveAll(emissions);
        //assert
        assertEquals(2, emissionList.size());
    }

    @Test
    void ShouldDeleteEmissionByType() {
        //act
        jpaEmissionRepository.deleteByType("nuclear");
        Emission found = jpaEmissionRepository.getByType("nuclear");
        //assert
        assertNull(found);
    }
}