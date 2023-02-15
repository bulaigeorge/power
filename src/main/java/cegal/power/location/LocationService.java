package cegal.power.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> saveAll(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }

    public Location findById(String id) {
        return locationRepository.findById(id);
    }

    public void deleteById(String id) {
        locationRepository.deleteById(id);
    }
}
