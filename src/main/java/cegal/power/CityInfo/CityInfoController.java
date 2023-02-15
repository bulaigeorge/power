package cegal.power.CityInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@CrossOrigin
public class CityInfoController {

    @Autowired
    private CityInfoService cityInfoService;

    @GetMapping
    ResponseEntity<List<CityInfo>> findALlCities() {
        return ResponseEntity.ok(cityInfoService.findALlCities());
    }

    @GetMapping("/city")
    ResponseEntity<CityInfo> findCityByName(@PathVariable String city) {
        CityInfo cityInfo = cityInfoService.findByCity(city);
        if (cityInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cityInfo);
    }

    @DeleteMapping("/{city}")
    ResponseEntity<Void> deleteCityInfoByName(@PathVariable String city) {
        cityInfoService.deleteByCity(city);
        return ResponseEntity.noContent().build();
    }

}
