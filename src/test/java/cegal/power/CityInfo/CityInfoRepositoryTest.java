package cegal.power.CityInfo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CityInfoRepositoryTest {

    @Autowired
    private JpaCityInfoRepository jpaCityInfoRepository;

    String[] months = new String[]{"01", "02", "03"};

    private CityInfo oslo = new CityInfo("Oslo", 9999, 14903, 23000, Arrays.asList(months));
    private CityInfo paris = new CityInfo("Paris", 9999, 14903, 23000, Arrays.asList(months));
    private CityInfo berlin = new CityInfo("Berlin", 9999, 14903, 23000, Arrays.asList(months));

    List<CityInfo> cities = new ArrayList<>();

    @Test
    @Order(2)
    void ShouldFindALlCities() {
        //act
        List<CityInfo> cities = (List<CityInfo>) jpaCityInfoRepository.findAll();
        //assert
        assertEquals(1, cities.size());
    }

    @Test
    @Order(1)
    void ShouldSaveCity() {
        //act
        CityInfo saved = jpaCityInfoRepository.save(oslo);
        //assert
        assertEquals(saved, oslo);
    }

    @Test
    @Order(2)
    void ShouldFindByCity() {
        //act
        CityInfo found = jpaCityInfoRepository.getByCity("Oslo");
        //assert
        assertEquals("Oslo", found.getCity());
    }

    @Test
    void deleteByCity() {
        //act
        jpaCityInfoRepository.deleteByCity("Oslo");
        CityInfo found = jpaCityInfoRepository.getByCity("Oslo");
        //assert
        assertNull(found);
    }
}