package cegal.power.CityInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityInfoService {

    @Autowired
    private CityInfoRepository cityInfoRepository;

    public List<CityInfo> findALlCities() {
        return cityInfoRepository.findALl();
    }

    public CityInfo saveCity(CityInfo cityInfo) {
        return cityInfoRepository.save(cityInfo);
    }

    public CityInfo findByCity(String city) {
        return cityInfoRepository.findByCity(city);
    }

    public void deleteByCity(String city) {
        cityInfoRepository.deleteByCity(city);
    }
}
