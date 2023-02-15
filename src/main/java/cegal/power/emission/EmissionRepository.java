package cegal.power.emission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmissionRepository {

    @Autowired
    private JpaEmissionRepository jpaEmissionRepository;

    public List<Emission> findAll() {
        return (List<Emission>) jpaEmissionRepository.findAll();
    }

    public Emission findByType(String type) {
        return jpaEmissionRepository.getByType(type);
    }

    public Emission save(Emission emission) {
        return jpaEmissionRepository.save(emission);
    }

    public List<Emission> saveAll(List<Emission> emissions) {
        return (List<Emission>) jpaEmissionRepository.saveAll(emissions);
    }

    public void deleteByType(String type) {
        jpaEmissionRepository.deleteByType(type);
    }
}
