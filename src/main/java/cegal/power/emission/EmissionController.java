package cegal.power.emission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/emission")
@CrossOrigin
public class EmissionController {


    private final EmissionService emissionService;
    @Autowired
    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @GetMapping
    ResponseEntity<List<Emission>> findAllEmissions() {
        return ResponseEntity.ok(emissionService.findALlEmissions());
    }

    @GetMapping("/{type}")
    ResponseEntity<Emission> findEmission(@PathVariable String type) {
        Emission emission = emissionService.findEmission(type);
        if (emission == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emission);
    }

    @PostMapping
    ResponseEntity<Emission> saveEmission(@RequestBody Emission emission) {
        Emission found = emissionService.findEmission(emission.getType());
        if (found != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Emission created;
        try {
            created = emissionService.saveEmission(emission);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("api/emission/" + created.getId())).body(created);
    }

    @PostMapping("/all")
    ResponseEntity<List<Emission>> saveAllEmissions(@RequestBody List<Emission> emissions) {
        List<Emission> created;
        try {
            created = emissionService.saveAll(emissions);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("api/emission/")).body(created);
    }

    @PutMapping
    ResponseEntity<Emission> updateEmission(@RequestBody Emission emission) {
        try {
            Emission found = emissionService.findEmission(emission.getType());
            emission.setId(found.getId());
            Emission updated = emissionService.saveEmission(emission);
            return ResponseEntity.ok(updated);
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{type}")
    ResponseEntity<Void> deleteEmission(@PathVariable String type) {
        emissionService.deleteByType(type);
        return ResponseEntity.noContent().build();
    }
}
