package cegal.power.location;

import cegal.power.emission.EmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    EmissionService emissionService;

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location findById(String id) {
        return locationRepository.findById(id);
    }

    public void deleteById(String id) {
        locationRepository.deleteById(id);
    }
}
