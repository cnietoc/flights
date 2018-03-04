package es.cnieto.flights.infrastructure.spring;

import es.cnieto.flights.domain.InterconnectedFlightService;
import es.cnieto.flights.domain.InterconnectedRouteService;
import es.cnieto.flights.domain.primary.InterconnectionsService;
import es.cnieto.flights.domain.secondary.FlightsRepository;
import es.cnieto.flights.domain.secondary.RoutesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainSpringConfiguration {
    private final FlightsRepository flightsRepository;
    private final RoutesRepository routesRepository;

    public DomainSpringConfiguration(FlightsRepository flightsRepository, RoutesRepository routesRepository) {
        this.flightsRepository = flightsRepository;
        this.routesRepository = routesRepository;
    }

    @Bean
    public InterconnectionsService interconnectionsService() {
        return new InterconnectionsService(interconnectedRouteService(), interconnectedFlightService());
    }

    @Bean
    InterconnectedRouteService interconnectedRouteService() {
        return new InterconnectedRouteService(routesRepository);
    }

    @Bean
    InterconnectedFlightService interconnectedFlightService() {
        return new InterconnectedFlightService(flightsRepository);
    }
}
