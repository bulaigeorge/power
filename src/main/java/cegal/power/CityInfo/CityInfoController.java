package cegal.power.CityInfo;

import cegal.power.CityInfo.CityInfoDTOs.CityInfoMonthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/information")
@CrossOrigin
public class CityInfoController {

    @Autowired
    private CityInfoService cityInfoService;

    @GetMapping
    ResponseEntity<List<CityInfo>> findALlCities() {
        return ResponseEntity.ok(cityInfoService.findALlCities());
    }

    @GetMapping("/{city}")
    ResponseEntity<CityInfo> findCityByName(@PathVariable String city) {
        CityInfo cityInfo = cityInfoService.findByCity(city);
        if (cityInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cityInfo);
    }

    @GetMapping("/{city}/{month}")
    ResponseEntity<CityInfoMonthDTO> findCityByNameAndMonth(@PathVariable String city, @PathVariable String month) {
        CityInfoMonthDTO cityInfo = cityInfoService.findByCityAndMonth(city, month);
        if (cityInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cityInfo);
    }

    @PostMapping("/{city}")
    ResponseEntity<?> saveCityInfo(@PathVariable String city) {
        CityInfo cityInfo = cityInfoService.findByCity(city);
        if (cityInfo != null) {
            return new ResponseEntity<Error>(HttpStatus.CONFLICT);
        }
        CityInfo created;
        try {
            created = cityInfoService.saveCity(city);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("api/information/" + created.getId())).body(created);
    }

    @DeleteMapping("/{city}")
    ResponseEntity<Void> deleteCityInfoByName(@PathVariable String city) {
        cityInfoService.deleteByCity(city);
        return ResponseEntity.noContent().build();
    }

}
