package cegal.power.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepository {

    @Autowired
    private JpaLocationRepository jpaLocationRepository;

    public List<Location> findAll() {
        return (List<Location>) jpaLocationRepository.findAll();
    }

    public List<Location> findLocationsByCity(String city) {
        return jpaLocationRepository.getByCity(city);
    }

    public List<Location> findLocationsByCityAndMonth(String city, String month) {
        return jpaLocationRepository.getByCityAndMonth(city, month);
    }

    public Location save(Location location) {
        return jpaLocationRepository.save(location);
    }

    public List<Location> saveAll(List<Location> locations) {
        return (List<Location>) jpaLocationRepository.saveAll(locations);
    }

    public Location findById(String id) {
        return jpaLocationRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        jpaLocationRepository.deleteById(id);
    }
}
