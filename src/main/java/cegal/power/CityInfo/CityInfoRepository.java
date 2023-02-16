package cegal.power.CityInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityInfoRepository {

    private final JpaCityInfoRepository jpaCityInfoRepository;
    @Autowired
    public CityInfoRepository(JpaCityInfoRepository jpaCityInfoRepository) {
        this.jpaCityInfoRepository = jpaCityInfoRepository;
    }

    public List<CityInfo> findALl() {
        return (List<CityInfo>) jpaCityInfoRepository.findAll();
    }

    public CityInfo save(CityInfo cityInfo) {
        return jpaCityInfoRepository.save(cityInfo);
    }

    public CityInfo findByCity(String city) {
        return jpaCityInfoRepository.getByCity(city);
    }

    public void deleteByCity(String city) {
        jpaCityInfoRepository.deleteByCity(city);
    }
}
