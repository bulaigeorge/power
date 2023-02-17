package cegal.power;

import cegal.power.CityInfo.CityInfo;
import cegal.power.CityInfo.CityInfoDTOs.CityInfoMonthDTO;
import cegal.power.CityInfo.CityInfoService;
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
import java.util.Arrays;
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

    @Bean
    @Primary
    public CityInfoService mockCityInfoService() {
        CityInfoService cityInfoService = Mockito.mock(CityInfoService.class);
        String[] months = new String[]{"01", "02", "03"};
        CityInfo oslo = new CityInfo("Oslo", 9999, 14903, 23000, Arrays.asList(months));
        CityInfo paris = new CityInfo("Paris", 9999, 14903, 23000, Arrays.asList(months));
        CityInfo berlin = new CityInfo("Berlin", 9999, 14903, 23000, Arrays.asList(months));

        Mockito.when(cityInfoService.findALlCities()).thenReturn(List.of(oslo, paris));
        Mockito.when(cityInfoService.findByCity("Berlin")).thenReturn(berlin);
        Mockito.when(cityInfoService.findByCity("Paris")).thenReturn(paris);
        Mockito.when(cityInfoService.findByCityAndMonth("Berlin", "01")).thenReturn(new CityInfoMonthDTO("Berlin", "01", 3000));

        Mockito.when(cityInfoService.saveCity("Oslo")).thenReturn(oslo);
        Mockito.when(cityInfoService.updateCity(paris)).thenReturn(paris);

        return cityInfoService;
    }
}
