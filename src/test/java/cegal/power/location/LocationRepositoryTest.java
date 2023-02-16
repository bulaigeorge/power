package cegal.power.location;

import cegal.power.location.LocationDTOs.LocationDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocationRepositoryTest {

    @Autowired
    private JpaLocationRepository jpaLocationRepository;
    private LocationDTO oslo = new LocationDTO("01", 2022, "Oslo", "hydro", 40, 120, "Akerselva");
    private LocationDTO paris = new LocationDTO("01", 2022, "Paris", "nuclear", 100, 3, "Mongolia");
    private LocationDTO berlin = new LocationDTO("01", 2022, "Berlin", "wind", 50, 70, "landbased");

    @Test
    @Order(2)
    void ShouldFindAllLocations() {
        //act
        List<Location> locations = (List<Location>) jpaLocationRepository.findAll();
        //assert
        assertEquals(3, locations.size());
    }

    @Test
    @Order(2)
    void ShouldfindLocationsByCity() {
        //act
        List<Location> found = jpaLocationRepository.getByCity("Oslo");
        //assert
        assertEquals("Oslo", found.get(0).getCity());
    }

    @Test
    @Order(2)
    void ShouldFindLocationsByCityAndMonth() {
        //act
        List<Location> found = jpaLocationRepository.getByCityAndMonth("Paris", "01");
        //assert
        assertEquals("nuclear", found.get(0).getPowerType());
    }

    @Test
    @Order(1)
    void ShouldSaveLocation() {
        //arrange
        Location location = new Location(oslo);
        //act
        Location saved = jpaLocationRepository.save(location);
        //assert
        assertEquals(saved, location);
    }

    @Test
    @Order(1)
    void ShouldSaveAllLocations() {
        //arrange
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(paris));
        locations.add(new Location(berlin));
        //act
        List<Location> locationList = (List<Location>) jpaLocationRepository.saveAll(locations);
        //assert
        assertEquals(2, locationList.size());
    }

    @Test
    @Order(3)
    void findById() {
        //act
        Location location = jpaLocationRepository.findById("1").orElse(null);
        //assert
        assertNotNull(location);
        assertEquals(1, location.getId());
    }

    @Test
    void deleteById() {
        //act
        jpaLocationRepository.deleteById("1");
        Location location = jpaLocationRepository.findById("1").orElse(null);
        //assert
        assertNull(location);
    }
}