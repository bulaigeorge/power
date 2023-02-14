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
}
