package es.cnieto.flights.domain;

import org.junit.Test;

import static es.cnieto.flights.domain.FlightBuilder.aFlight;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class FlightTest {
    @Test
    public void departureIsAfterWithOneHourLater() {
        Flight flight = aFlight().withDepartureDateTime(now()).build();

        assertFalse(flight.departureIsAfter(now().plus(1, HOURS)));
    }

    @Test
    public void departureIsAfterWithOneHourBefore() {
        Flight flight = aFlight().withDepartureDateTime(now()).build();

        assertTrue(flight.departureIsAfter(now().minus(1, HOURS)));
    }

    @Test
    public void arrivalIsBeforeWithOneHourLater() {
        Flight flight = aFlight().withArrivalDateTime(now()).build();

        assertTrue(flight.arrivalIsBefore(now().plus(1, HOURS)));
    }

    @Test
    public void arrivalIsBeforeWithOneHourBefore() {
        Flight flight = aFlight().withArrivalDateTime(now()).build();

        assertFalse(flight.arrivalIsBefore(now().minus(1, HOURS)));
    }
}