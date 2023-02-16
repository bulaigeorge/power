package cegal.power;

import cegal.power.emission.Emission;

import cegal.power.emission.EmissionService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.test.context.ActiveProfiles;

@Configuration
@ActiveProfiles("test")
public class TestConfig {

    @Bean
    @Primary
    public EmissionService mockService() {
        EmissionService emissionService = Mockito.mock(EmissionService.class);
        Mockito.when(emissionService.findEmission("hydro")).thenReturn(new Emission("mockedHydro", 23));

        return emissionService;
    }
}
