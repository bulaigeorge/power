package cegal.power.location;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaLocationRepository extends CrudRepository<Location, String> {

    List<Location> getByCity(String city);

    List<Location> getByCityAndMonth(String city, String month);
}
