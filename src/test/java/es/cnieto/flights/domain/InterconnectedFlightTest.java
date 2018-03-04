package es.cnieto.flights.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static es.cnieto.flights.domain.InterconnectedFlightBuilder.anInterconnectedFlight;
import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectedFlightTest {
    private static final LocalDateTime A_DATE_TIME = now();

    @Mock
    private Flight firstFlight, secondFlight, otherFlight;

    @Test
    public void isInterconnectedWith() {
        when(secondFlight.isInterconnectedWith(otherFlight)).thenReturn(true);

        InterconnectedFlight interconnectedFlight = createInterconnectedFlight();

        assertTrue(interconnectedFlight.isInterconnectedWith(otherFlight));
    }

    @Test
    public void departureIsAfter() {
        when(firstFlight.departureIsAfter(A_DATE_TIME)).thenReturn(true);

        InterconnectedFlight interconnectedFlight = createInterconnectedFlight();

        assertTrue(interconnectedFlight.departureIsAfter(A_DATE_TIME));
    }

    @Test
    public void arrivalIsBefore() {
        when(secondFlight.arrivalIsBefore(A_DATE_TIME)).thenReturn(true);

        InterconnectedFlight interconnectedFlight = createInterconnectedFlight();

        assertTrue(interconnectedFlight.arrivalIsBefore(A_DATE_TIME));
    }

    @Test
    public void getStops() {
        InterconnectedFlight interconnectedFlight = createInterconnectedFlight();

        assertThat(interconnectedFlight.getStops(), is(1));
    }

    private InterconnectedFlight createInterconnectedFlight() {
        return anInterconnectedFlight()
                .withFlights(asList(firstFlight, secondFlight))
                .build();
    }
}