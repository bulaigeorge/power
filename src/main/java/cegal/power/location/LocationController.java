package cegal.power.location;

import cegal.power.location.LocationDTOs.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/location")
@CrossOrigin
public class LocationController {


    private final LocationService locationService;
    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    ResponseEntity<List<Location>> findAllLocations() {
        return ResponseEntity.ok(locationService.findAllLocations());
    }

    @GetMapping("/{id}")
    ResponseEntity<Location> findLocationById(@PathVariable String id) {
        Location location = locationService.findById(id);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }

    @PostMapping
    ResponseEntity<?> saveLocation(@RequestBody LocationDTO locationDTO) {
        Location location = new Location(locationDTO);

        if (locationService.findAllLocations().contains(location)) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        };
        Location created;
        try {
            created = locationService.saveLocation(location);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("api/location/" + created.getId())).body(created);
    }

    @PostMapping("/all")
    ResponseEntity<List<Location>> saveAllLocations(@RequestBody List<LocationDTO> locationsDTO) {
        List<Location> locations = locationsDTO.stream().map(Location::new).toList();

        List<Location> created;
        try {
            created = locationService.saveAll(locations);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("api/location/")).body(created);
    }

    @PutMapping("/{id}")
    ResponseEntity<Location> updateLocation(@PathVariable String id, @RequestBody Location location) {
        try {
            Location found = locationService.findById(id);
            location.setId(found.getId());
            Location updated = locationService.saveLocation(location);
            return ResponseEntity.ok(updated);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteLocation(@PathVariable String id) {
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
