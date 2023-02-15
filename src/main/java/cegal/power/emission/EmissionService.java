package cegal.power.emission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionService {

    @Autowired
    private EmissionRepository emissionRepository;

    public List<Emission> findALlEmissions() {
        return emissionRepository.findAll();
    }

    public Emission findEmission(String type) {
        return emissionRepository.findByType(type);
    }

    public Emission saveEmission(Emission emission) {
        return emissionRepository.save(emission);
    }

    public List<Emission> saveAll(List<Emission> emissions) {
        return emissionRepository.saveAll(emissions);
    }

    public void deleteByType(String type) {
        emissionRepository.deleteByType(type);
    }
}
