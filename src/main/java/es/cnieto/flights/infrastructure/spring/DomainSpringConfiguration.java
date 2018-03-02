package es.cnieto.flights.infrastructure.spring;

import es.cnieto.flights.domain.primary.InterconnectionsService;
import es.cnieto.flights.domain.secondary.FlightsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainSpringConfiguration {
    private final FlightsRepository flightsRepository;

    public DomainSpringConfiguration(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    @Bean
    public InterconnectionsService interconnectionsService() {
        return new InterconnectionsService(flightsRepository);
    }
}
