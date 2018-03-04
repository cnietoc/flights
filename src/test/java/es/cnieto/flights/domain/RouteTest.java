package es.cnieto.flights.domain;

import org.junit.Test;

import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteTest {
    private static final String BCN_AIRPORT = "BCN";
    private static final String MAD_AIRPORT = "MAD";

    @Test
    public void arriveToWhenItsTrue() {
        Route route = aRoute().withArrivalAirport(BCN_AIRPORT).build();

        assertTrue(route.arriveTo(BCN_AIRPORT));
    }

    @Test
    public void arriveToWhenItsFalse() {
        Route route = aRoute().withArrivalAirport(MAD_AIRPORT).build();

        assertFalse(route.arriveTo(BCN_AIRPORT));
    }

    @Test
    public void departureFromWhenItsTrue() {
        Route route = aRoute().withDepartureAirport(BCN_AIRPORT).build();

        assertTrue(route.departureFrom(BCN_AIRPORT));
    }

    @Test
    public void departureFromWhenItsFalse() {
        Route route = aRoute().withDepartureAirport(MAD_AIRPORT).build();

        assertFalse(route.departureFrom(BCN_AIRPORT));
    }

}