package cegal.power.emission;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

public interface JpaEmissionRepository extends CrudRepository<Emission, String> {
    Emission getByType(String type);

    @Transactional
    void deleteByType(String type);
}
