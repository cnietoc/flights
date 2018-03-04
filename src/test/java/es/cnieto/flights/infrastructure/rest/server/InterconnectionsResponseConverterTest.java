package es.cnieto.flights.infrastructure.rest.server;

import es.cnieto.flights.infrastructure.spring.RestServerConverterSpringConfiguration;

import static es.cnieto.flights.domain.InterconnectionsBuilder.anInterconnections;

public class InterconnectionsResponseConverterTest {
    private InterconnectionsResponseConverter interconnectionsResponseConverter = new RestServerConverterSpringConfiguration().interconnectionsResponseConverter();

    // TODO @Test
    public void convertAsExpected() {
        interconnectionsResponseConverter.from(
                anInterconnections()
                        .with(null)
                        .build());

    }
}