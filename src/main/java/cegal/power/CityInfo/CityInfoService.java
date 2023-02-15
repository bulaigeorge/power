package cegal.power.CityInfo;

import cegal.power.CityInfo.CityInfoDTOs.CityInfoMonthDTO;
import cegal.power.emission.EmissionRepository;
import cegal.power.location.Location;
import cegal.power.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityInfoService {

    @Autowired
    private CityInfoRepository cityInfoRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EmissionRepository emissionRepository;

    public List<CityInfo> findALlCities() {
        return cityInfoRepository.findALl();
    }

    public CityInfo saveCity(String city) {
        List<Location> locationsByCity = locationRepository.findLocationsByCity(city);

        int totalConsumption = locationsByCity.stream()
                .mapToInt(Location::getUnits)
                .sum();

        int totalEmission = locationsByCity.stream()
                .mapToInt(location ->
                        location.getUnits() * (emissionRepository.findByType(location.getPowerType())).getEmission())
                .sum();

        int totalCost = locationsByCity.stream()
                .mapToInt(location -> location.getUnits() * location.getUnitPrice())
                .sum();

        CityInfo cityInfo = new CityInfo(city, totalConsumption, totalEmission, totalCost);

        cityInfo.setMonths(locationsByCity.stream().map(Location::getMonth).distinct().toList());

        return cityInfoRepository.save(cityInfo);
    }

    public CityInfo findByCity(String city) {
        return cityInfoRepository.findByCity(city);
    }

    public void deleteByCity(String city) {
        cityInfoRepository.deleteByCity(city);
    }

    public CityInfoMonthDTO findByCityAndMonth(String city, String month) {
        List<Location> locationsByCityAndMonth = locationRepository.findLocationsByCityAndMonth(city, month);

        int totalEmission = locationsByCityAndMonth.stream()
                .mapToInt(location ->
                        location.getUnits() * (emissionRepository.findByType(location.getPowerType())).getEmission())
                .sum();


        return new CityInfoMonthDTO(city, month, totalEmission);
    }
}
