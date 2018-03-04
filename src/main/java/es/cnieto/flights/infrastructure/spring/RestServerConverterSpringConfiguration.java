package es.cnieto.flights.infrastructure.spring;

import es.cnieto.flights.infrastructure.rest.server.InterconnectionsResponseConverter;
import es.cnieto.flights.infrastructure.rest.server.LegResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestServerConverterSpringConfiguration {
    @Bean
    public InterconnectionsResponseConverter interconnectionsResponseConverter() {
        return new InterconnectionsResponseConverter(legResponseConverter());
    }

    @Bean
    LegResponseConverter legResponseConverter() {
        return new LegResponseConverter();
    }
}
