package cegal.power;

import cegal.power.emission.Emission;

import cegal.power.emission.EmissionService;
import cegal.power.location.Location;
import cegal.power.location.LocationDTOs.LocationDTO;
import cegal.power.location.LocationService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ActiveProfiles("test")
public class TestConfig {

    @Bean
    @Primary
    public EmissionService mockEmissionService() {
        EmissionService emissionService = Mockito.mock(EmissionService.class);
        Emission hydro = new Emission("hydro", 23);
        Emission nuclear = new Emission("nuclear", 12);
        Emission wind = new Emission("wind", 12);
        Emission coal = new Emission("coal", 820);
        List<Emission> emissions = new ArrayList<>();
        emissions.add(nuclear);
        emissions.add(wind);
        emissions.add(coal);

        Mockito.when(emissionService.findEmission("hydro")).thenReturn(hydro);
        Mockito.when(emissionService.findEmission("wind")).thenReturn(wind);
        Mockito.when(emissionService.findALlEmissions()).thenReturn(List.of(nuclear, coal, wind));

        Mockito.when(emissionService.saveEmission(coal)).thenReturn(coal);
        Mockito.when(emissionService.saveEmission(wind)).thenReturn(wind);
        Mockito.when(emissionService.saveAll(emissions)).thenReturn(List.of(nuclear, coal, wind));

        return emissionService;
    }

    @Bean
    @Primary
    public LocationService mockLocationService() {
        LocationService locationService = Mockito.mock(LocationService.class);
        LocationDTO oslo = new LocationDTO("01", 2022, "Oslo", "hydro", 40, 120, "Akerselva");
        LocationDTO paris = new LocationDTO("01", 2022, "Paris", "nuclear", 100, 3, "Mongolia");
        LocationDTO berlin = new LocationDTO("01", 2022, "Berlin", "wind", 50, 70, "landbased");
        LocationDTO stockholm = new LocationDTO("01", 2022, "Stockholm", "wind", 50, 70, "landbased");
        List<Location> locations = new ArrayList<>();
        locations.add(new Location(oslo));
        locations.add(new Location(paris));
        locations.add(new Location(berlin));


        Mockito.when(locationService.findAllLocations()).thenReturn(List.of(new Location(oslo), new Location(paris), new Location(berlin)));
        Mockito.when(locationService.findById("1")).thenReturn(new Location(oslo));

        Mockito.when(locationService.saveLocation(new Location(stockholm))).thenReturn((new Location(stockholm)));
        Mockito.when(locationService.saveLocation(new Location(oslo))).thenReturn((new Location(oslo)));
        Mockito.when(locationService.saveAll(locations)).thenReturn(List.of(new Location(oslo), new Location(paris), new Location(berlin)));

        return locationService;
    }
}
