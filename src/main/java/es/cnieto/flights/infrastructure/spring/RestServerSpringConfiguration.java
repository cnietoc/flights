package es.cnieto.flights.infrastructure.spring;

import es.cnieto.flights.domain.primary.InterconnectionsService;
import es.cnieto.flights.infrastructure.rest.server.InterconnectionsController;
import es.cnieto.flights.infrastructure.rest.server.InterconnectionsResponseConverter;
import es.cnieto.flights.infrastructure.rest.server.LegResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestServerSpringConfiguration {
    private final InterconnectionsService interconnectionsService;

    public RestServerSpringConfiguration(InterconnectionsService interconnectionsService) {
        this.interconnectionsService = interconnectionsService;
    }

    @Bean
    public InterconnectionsController interconnectionsController() {
        return new InterconnectionsController(interconnectionsService, interconnectionsResponseConverter());
    }

    @Bean
    public InterconnectionsResponseConverter interconnectionsResponseConverter() {
        return new InterconnectionsResponseConverter(legResponseConverter());
    }

    @Bean
    LegResponseConverter legResponseConverter() {
        return new LegResponseConverter();
    }
}
