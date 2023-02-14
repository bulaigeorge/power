package cegal.power.emission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/emission")
@CrossOrigin
public class EmissionController {

    @Autowired
    private EmissionService emissionService;

    @GetMapping
    ResponseEntity<List<Emission>> findAllEmissions() {
        return ResponseEntity.ok(emissionService.findALlEmissions());
    }
    @GetMapping("/{type}")
    ResponseEntity<Emission> findEmission(@PathVariable String type) {
        if (emissionService.findEmission(type) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(emissionService.findEmission(type));
    }

    @PostMapping
    ResponseEntity<Emission> saveEmission(@RequestBody Emission emission) {
        Emission created;
        try {
            created = emissionService.saveEmission(emission);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("api/emission/" + created.getId())).body(created);
    }

    @PutMapping("/{type}")
    ResponseEntity<Emission> updateEmission(@PathVariable String type, @RequestBody Emission emission) {
        try {
            Emission found = emissionService.findEmission(type);
            emission.setId(found.getId());
            Emission updated = emissionService.saveEmission(emission);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{type}")
    ResponseEntity<Void> deleteEmission(@PathVariable String type) {
        emissionService.deleteByType(type);
        return ResponseEntity.noContent().build();
    }
}
