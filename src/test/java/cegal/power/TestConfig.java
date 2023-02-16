package cegal.power;

import cegal.power.emission.Emission;

import cegal.power.emission.EmissionService;
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
    public EmissionService mockService() {
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
}
