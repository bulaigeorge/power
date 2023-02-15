package cegal.power.CityInfo;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface JpaCityInfoRepository extends CrudRepository<CityInfo, String> {

    CityInfo getByCity(String city);

    @Transactional
    void deleteByCity(String city);
}
