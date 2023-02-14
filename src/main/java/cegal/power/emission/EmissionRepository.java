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

    public Emission save(Emission emission) {
        return jpaEmissionRepository.save(emission);
    }
}
