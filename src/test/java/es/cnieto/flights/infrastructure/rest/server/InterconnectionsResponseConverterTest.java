package es.cnieto.flights.infrastructure.rest.server;

import es.cnieto.flights.infrastructure.rest.server.api.InterconnectionResponse;
import es.cnieto.flights.infrastructure.spring.RestServerConverterSpringConfiguration;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static es.cnieto.flights.domain.FlightBuilder.aFlight;
import static es.cnieto.flights.domain.InterconnectedFlightBuilder.anInterconnectedFlight;
import static es.cnieto.flights.domain.InterconnectionsBuilder.anInterconnections;
import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static es.cnieto.flights.infrastructure.rest.server.api.InterconnectionResponseBuilder.anInterconnectionResponse;
import static es.cnieto.flights.infrastructure.rest.server.api.LegResponseBuilder.aLegResponse;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class InterconnectionsResponseConverterTest {
    private static final LocalDateTime FIRST_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(2018, 4, 20, 22, 15);
    private static final LocalDateTime FIRST_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(2018, 4, 20, 23, 30);
    private static final String FIRST_FLIGHT_DEPARTURE_AIRPORT = "BCN";
    private static final String FIRST_FLIGHT_ARRIVAL_AIRPORT = "MAD";

    private static final LocalDateTime SECOND_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(2018, 4, 19, 5, 20);
    private static final LocalDateTime SECOND_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(2018, 4, 19, 8, 45);
    private static final String SECOND_FLIGHT_DEPARTURE_AIRPORT = "LON";
    private static final String SECOND_FLIGHT_ARRIVAL_AIRPORT = "WRO";

    private static final LocalDateTime THIRD_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(2018, 4, 20, 6, 5);
    private static final LocalDateTime THIRD_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(2018, 4, 20, 7, 30);
    private static final String THIRD_FLIGHT_DEPARTURE_AIRPORT = "DUB";
    private static final String THIRD_FLIGHT_ARRIVAL_AIRPORT = "BVA";

    private InterconnectionsResponseConverter interconnectionsResponseConverter = new RestServerConverterSpringConfiguration().interconnectionsResponseConverter();

    @Test
    public void convertAsExpected() {
        List<InterconnectionResponse> interconnectionResponses = interconnectionsResponseConverter
                .from(anInterconnections()
                        .with(asList(
                                anInterconnectedFlight().with(
                                        singletonList(aFlight().withDepartureDateTime(FIRST_FLIGHT_DEPARTURE_DATE_TIME)
                                                .withArrivalDateTime(FIRST_FLIGHT_ARRIVAL_DATE_TIME)
                                                .withRoute(
                                                        aRoute().from(FIRST_FLIGHT_DEPARTURE_AIRPORT)
                                                                .to(FIRST_FLIGHT_ARRIVAL_AIRPORT)
                                                                .build()
                                                ).build()
                                        )
                                ).build(),
                                anInterconnectedFlight().with(
                                        asList(aFlight().withDepartureDateTime(SECOND_FLIGHT_DEPARTURE_DATE_TIME)
                                                        .withArrivalDateTime(SECOND_FLIGHT_ARRIVAL_DATE_TIME)
                                                        .withRoute(
                                                                aRoute().from(SECOND_FLIGHT_DEPARTURE_AIRPORT)
                                                                        .to(SECOND_FLIGHT_ARRIVAL_AIRPORT)
                                                                        .build()
                                                        ).build(),
                                                aFlight().withDepartureDateTime(THIRD_FLIGHT_DEPARTURE_DATE_TIME)
                                                        .withArrivalDateTime(THIRD_FLIGHT_ARRIVAL_DATE_TIME)
                                                        .withRoute(
                                                                aRoute().from(THIRD_FLIGHT_DEPARTURE_AIRPORT)
                                                                        .to(THIRD_FLIGHT_ARRIVAL_AIRPORT)
                                                                        .build()
                                                        ).build()
                                        )
                                ).build()))
                        .build());

        assertThat(interconnectionResponses,
                equalTo(asList(
                        anInterconnectionResponse()
                                .withStops(0)
                                .withLegs(singletonList(
                                        aLegResponse()
                                                .withDepartureAirport(FIRST_FLIGHT_DEPARTURE_AIRPORT)
                                                .withDepartureDateTime(FIRST_FLIGHT_DEPARTURE_DATE_TIME)
                                                .withArrivalAirport(FIRST_FLIGHT_ARRIVAL_AIRPORT)
                                                .withArrivalDateTime(FIRST_FLIGHT_ARRIVAL_DATE_TIME)
                                                .build()))
                                .build()
                        , anInterconnectionResponse()
                                .withStops(1)
                                .withLegs(asList(
                                        aLegResponse()
                                                .withDepartureAirport(SECOND_FLIGHT_DEPARTURE_AIRPORT)
                                                .withDepartureDateTime(SECOND_FLIGHT_DEPARTURE_DATE_TIME)
                                                .withArrivalAirport(SECOND_FLIGHT_ARRIVAL_AIRPORT)
                                                .withArrivalDateTime(SECOND_FLIGHT_ARRIVAL_DATE_TIME)
                                                .build(),
                                        aLegResponse()
                                                .withDepartureAirport(THIRD_FLIGHT_DEPARTURE_AIRPORT)
                                                .withDepartureDateTime(THIRD_FLIGHT_DEPARTURE_DATE_TIME)
                                                .withArrivalAirport(THIRD_FLIGHT_ARRIVAL_AIRPORT)
                                                .withArrivalDateTime(THIRD_FLIGHT_ARRIVAL_DATE_TIME)
                                                .build()))
                                .build())));
    }
}