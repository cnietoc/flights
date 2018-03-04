package es.cnieto.flights.infrastructure.spring;

import es.cnieto.flights.infrastructure.rest.client.flights.FlightsConverter;
import es.cnieto.flights.infrastructure.rest.client.flights.RestFlightsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class RestFlightsRepositorySpringConfiguration {
    private final URI serviceEndpoint;

    public RestFlightsRepositorySpringConfiguration(@Value("${service.endpoint}") URI serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    @Bean
    public RestFlightsRepository restFlightsRepository() {
        return new RestFlightsRepository(new RestTemplate(), serviceEndpoint, flightsConverter());
    }

    @Bean
    FlightsConverter flightsConverter() {
        return new FlightsConverter();
    }
}
