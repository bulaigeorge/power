package cegal.power.location;


import cegal.power.emission.Emission;
import cegal.power.location.LocationDTOs.LocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/location")
@CrossOrigin
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    ResponseEntity<List<Location>> findAllLocations() {
        return ResponseEntity.ok(locationService.findAllLocations());
    }

    @GetMapping("/id")
    ResponseEntity<Location> findLocationById(@PathVariable String id) {
        Location location = locationService.findById(id);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }

    @PostMapping
    ResponseEntity<Location> saveLocation(@RequestBody LocationDTO locationDTO) {
        Location location = new Location(locationDTO.month(),
                String.valueOf(locationDTO.year()),
                locationDTO.city(),
                locationDTO.power(),
                locationDTO.unitPrice(),
                locationDTO.units(),
                locationDTO.source());
        Location created;
        try {
            created = locationService.saveLocation(location);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("api/location/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    ResponseEntity<Location> updateLocation(@PathVariable String id, @RequestBody Location location) {
        try {
            Location found = locationService.findById(id);
            location.setId(found.getId());
            Location updated = locationService.saveLocation(location);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteLocation(@PathVariable String id) {
        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
