package es.cnieto.flights.infrastructure.spring;

import es.cnieto.flights.infrastructure.rest.client.routes.RestRoutesRepository;
import es.cnieto.flights.infrastructure.rest.client.routes.RouteConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Configuration
public class RestRoutesRepositorySpringConfiguration {
    private final URI serviceEndpoint;

    public RestRoutesRepositorySpringConfiguration(@Value("${service.endpoint}") URI serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    @Bean
    public RestRoutesRepository restRoutesRepository() {
        return new RestRoutesRepository(new RestTemplate(), serviceEndpoint, routeConverter());
    }

    @Bean
    RouteConverter routeConverter() {
        return new RouteConverter();
    }
}
