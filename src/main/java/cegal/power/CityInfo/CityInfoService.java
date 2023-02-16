package cegal.power.CityInfo;

import cegal.power.CityInfo.CityInfoDTOs.CityInfoMonthDTO;
import cegal.power.emission.EmissionRepository;
import cegal.power.location.Location;
import cegal.power.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityInfoService {

    private final CityInfoRepository cityInfoRepository;
    private final LocationRepository locationRepository;
    private final EmissionRepository emissionRepository;
    @Autowired CityInfoService(CityInfoRepository cityInfoRepository, LocationRepository locationRepository, EmissionRepository emissionRepository) {
        this.cityInfoRepository = cityInfoRepository;
        this.locationRepository = locationRepository;
        this.emissionRepository = emissionRepository;
    }

    public List<CityInfo> findALlCities() {
        return cityInfoRepository.findALl();
    }

    public CityInfo saveCity(String city) {
        CityInfo cityInfo = calculateCityInfo(city);

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

    public CityInfo updateCity(CityInfo cityInfo) {
        CityInfo updated = calculateCityInfo(cityInfo.getCity());
        updated.setId(cityInfo.getId());
        return cityInfoRepository.save(updated);
    }

    public CityInfo calculateCityInfo(String city) {
        List<Location> locationsByCity = locationRepository.findLocationsByCity(city);

        int totalConsumption = 0, totalEmission = 0, totalCost = 0;
        List<String> months = new ArrayList<>();

        for (Location location : locationsByCity) {
            totalConsumption += location.getUnits();
            totalEmission += location.getUnits() * (emissionRepository.findByType(location.getPowerType())).getEmission();
            totalCost += location.getUnits() * location.getUnitPrice();

            if (!months.contains(location.getMonth())) {
                months.add(location.getMonth());
            }
        }

        return new CityInfo(city, totalConsumption, totalEmission, totalCost, months);
    }
}
