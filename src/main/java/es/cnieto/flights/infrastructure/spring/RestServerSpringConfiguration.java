package es.cnieto.flights.infrastructure.spring;

import es.cnieto.flights.domain.primary.InterconnectionsService;
import es.cnieto.flights.infrastructure.rest.server.InterconnectionsController;
import es.cnieto.flights.infrastructure.rest.server.InterconnectionsResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestServerSpringConfiguration {
    private final InterconnectionsService interconnectionsService;
    private final InterconnectionsResponseConverter interconnectionsResponseConverter;

    public RestServerSpringConfiguration(InterconnectionsService interconnectionsService,
                                         InterconnectionsResponseConverter interconnectionsResponseConverter) {
        this.interconnectionsService = interconnectionsService;
        this.interconnectionsResponseConverter = interconnectionsResponseConverter;
    }

    @Bean
    public InterconnectionsController interconnectionsController() {
        return new InterconnectionsController(interconnectionsService, interconnectionsResponseConverter);
    }
}
