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

    public Location save(Location location) {
        return jpaLocationRepository.save(location);
    }
}
