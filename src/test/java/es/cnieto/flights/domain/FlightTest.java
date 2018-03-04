package es.cnieto.flights.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static es.cnieto.flights.domain.FlightBuilder.aFlight;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class FlightTest {
    private static final String BCN_AIRPORT = "BCN";

    @Mock
    private Route route, otherRoute;

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

    @Test
    public void arriveTo() {
        when(route.arriveTo(BCN_AIRPORT)).thenReturn(true);
        Flight flight = aFlight().withRoute(route).build();

        assertTrue(flight.arriveTo(BCN_AIRPORT));
    }

    @Test
    public void departureFrom() {
        when(route.departureFrom(BCN_AIRPORT)).thenReturn(true);
        Flight flight = aFlight().withRoute(route).build();

        assertTrue(flight.departureFrom(BCN_AIRPORT));
    }

    @Test
    public void isInterconnected() {
        when(route.isInterconnectedWith(otherRoute)).thenReturn(true);
        Flight flight = aFlight()
                .withRoute(route)
                .withDepartureDateTime(now())
                .withArrivalDateTime(now().plus(2, DAYS))
                .build();

        assertTrue(flight.isInterconnectedWith(aFlight()
                .withRoute(otherRoute)
                .withDepartureDateTime(flight.getArrivalDateTime().plus(3, HOURS))
                .withArrivalDateTime(flight.getArrivalDateTime().plus(2, DAYS))
                .build()));
    }

    @Test
    public void isInterconnectedWhenIsNotSameRoute() {
        when(route.isInterconnectedWith(otherRoute)).thenReturn(false);
        Flight flight = aFlight()
                .withRoute(route)
                .withDepartureDateTime(now())
                .withArrivalDateTime(now().plus(2, DAYS))
                .build();

        assertFalse(flight.isInterconnectedWith(aFlight()
                .withRoute(otherRoute)
                .withDepartureDateTime(flight.getArrivalDateTime().plus(3, HOURS))
                .withArrivalDateTime(flight.getArrivalDateTime().plus(2, DAYS))
                .build()));
    }

    @Test
    public void isInterconnectedWhenIsBeforeArrival() {
        when(route.isInterconnectedWith(otherRoute)).thenReturn(true);
        Flight flight = aFlight()
                .withRoute(route)
                .withDepartureDateTime(now())
                .withArrivalDateTime(now().plus(2, DAYS))
                .build();

        assertFalse(flight.isInterconnectedWith(aFlight()
                .withRoute(otherRoute)
                .withDepartureDateTime(flight.getArrivalDateTime().minus(1, HOURS))
                .withArrivalDateTime(flight.getArrivalDateTime().plus(2, DAYS))
                .build()));
    }

    @Test
    public void isInterconnectedWhenIsNotMoreThan2Hours() {
        when(route.isInterconnectedWith(otherRoute)).thenReturn(true);
        Flight flight = aFlight()
                .withRoute(route)
                .withDepartureDateTime(now())
                .withArrivalDateTime(now().plus(2, DAYS))
                .build();

        assertFalse(flight.isInterconnectedWith(aFlight()
                .withRoute(otherRoute)
                .withDepartureDateTime(flight.getArrivalDateTime().plus(1, HOURS))
                .withArrivalDateTime(flight.getArrivalDateTime().plus(2, DAYS))
                .build()));
    }
}