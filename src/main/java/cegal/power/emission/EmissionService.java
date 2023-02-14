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
        List<Emission> emission =  emissionRepository.findAll()
                .stream()
                .filter(e -> e.getType().equals(type))
                .toList();

        return emission.size() == 0 ? null : emission.get(0);
    }

    public Emission saveEmission(Emission emission) {
        return emissionRepository.save(emission);
    }

    public void deleteByType(String type) {
        Emission deleted =  emissionRepository.findAll()
                .stream()
                .filter(e -> e.getType().equals(type))
                .toList()
                .get(0);

        emissionRepository.deleteById(String.valueOf(deleted.getId()));
    }
}
