package es.cnieto.flights.domain;

import org.junit.Test;

import java.util.List;

import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static es.cnieto.flights.domain.RoutesBuilder.aRoutes;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RoutesTest {
    private static final String BCN_AIRPORT = "BCN";
    private static final String MAD_AIRPORT = "MAD";
    private static final String DUB_AIRPORT = "DUB";

    @Test
    public void fromAirportAsExpected() {
        Routes routes = aRoutes().with(
                asList(aRoute().withDepartureAirport(BCN_AIRPORT)
                                .withArrivalAirport(MAD_AIRPORT)
                                .build(),
                        aRoute().withDepartureAirport(DUB_AIRPORT).withArrivalAirport(MAD_AIRPORT).build()))
                .build();

        List<Route> routesFromBCNAirport = routes.from(BCN_AIRPORT);

        assertThat(routesFromBCNAirport,
                equalTo(singletonList(
                        aRoute().withDepartureAirport(BCN_AIRPORT)
                                .withArrivalAirport(MAD_AIRPORT)
                                .build())));
    }
}