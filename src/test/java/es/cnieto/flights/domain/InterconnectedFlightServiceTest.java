package es.cnieto.flights.domain;

import es.cnieto.flights.domain.secondary.FlightsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static es.cnieto.flights.domain.FlightBuilder.aFlight;
import static es.cnieto.flights.domain.InterconnectedFlightBuilder.anInterconnectedFlight;
import static es.cnieto.flights.domain.InterconnectedRouteBuilder.anInterconnectedRoute;
import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectedFlightServiceTest {
    private static final int YEAR = 2018;
    private static final Month MONTH = Month.APRIL;
    private static final String BCN_AIRPORT = "BCN";
    private static final String MAD_AIRPORT = "MAD";
    private static final String LON_AIRPORT = "LON";


    private static final Route BCN_MAD_ROUTE = aRoute().from(BCN_AIRPORT).to(MAD_AIRPORT).build();
    private static final Route MAD_LON_ROUTE = aRoute().from(MAD_AIRPORT).to(LON_AIRPORT).build();

    private static final LocalDateTime FIRST_BCN_MAD_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 12, 22, 30);
    private static final LocalDateTime FIRST_BCN_MAD_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 12, 23, 30);
    private static final LocalDateTime SECOND_BCN_MAD_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 10, 12, 30);
    private static final LocalDateTime SECOND_BCN_MAD_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 10, 13, 30);

    private static final LocalDateTime FIRST_MAD_LON_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 10, 22, 30);
    private static final LocalDateTime FIRST_MAD_LON_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 10, 23, 30);
    private static final LocalDateTime SECOND_MAD_LON_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 1, 12, 30);
    private static final LocalDateTime SECOND_MAD_LON_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 1, 13, 30);


    private static final Flight FIRST_BCN_MAD_FLIGHT = aFlight()
            .withRoute(BCN_MAD_ROUTE)
            .withDepartureDateTime(FIRST_BCN_MAD_FLIGHT_DEPARTURE_DATE_TIME)
            .withArrivalDateTime(FIRST_BCN_MAD_FLIGHT_ARRIVAL_DATE_TIME)
            .build();
    private static final Flight SECOND_BCN_MAD_FLIGHT = aFlight()
            .withRoute(BCN_MAD_ROUTE)
            .withDepartureDateTime(SECOND_BCN_MAD_FLIGHT_DEPARTURE_DATE_TIME)
            .withArrivalDateTime(SECOND_BCN_MAD_FLIGHT_ARRIVAL_DATE_TIME)
            .build();

    private static final Flight MAD_LON_FLIGHT_INTERCONNECTED_WITH_SECOND_BCN_MAD_FLIGHT = aFlight()
            .withRoute(MAD_LON_ROUTE)
            .withDepartureDateTime(FIRST_MAD_LON_FLIGHT_DEPARTURE_DATE_TIME)
            .withArrivalDateTime(FIRST_MAD_LON_FLIGHT_ARRIVAL_DATE_TIME)
            .build();
    private static final Flight MAD_LON_FLIGHT_NOT_INTERCONNECTED = aFlight()
            .withRoute(MAD_LON_ROUTE)
            .withDepartureDateTime(SECOND_MAD_LON_FLIGHT_DEPARTURE_DATE_TIME)
            .withArrivalDateTime(SECOND_MAD_LON_FLIGHT_ARRIVAL_DATE_TIME)
            .build();


    @Mock
    private FlightsRepository flightsRepository;
    @InjectMocks
    private InterconnectedFlightService interconnectedFlightService;

    @Test
    public void getInterconnectedFlightWithDirectRoute() {
        when(flightsRepository.searchBy(BCN_MAD_ROUTE, YEAR, MONTH))
                .thenReturn(asList(
                        FIRST_BCN_MAD_FLIGHT,
                        SECOND_BCN_MAD_FLIGHT
                ));

        List<InterconnectedFlight> interconnectedFlights = interconnectedFlightService
                .from(anInterconnectedRoute()
                                .with(singletonList(
                                        BCN_MAD_ROUTE
                                )).build(),
                        YEAR,
                        MONTH);

        assertThat(interconnectedFlights,
                equalTo(asList(
                        anInterconnectedFlight().with(singletonList(FIRST_BCN_MAD_FLIGHT)).build(),
                        anInterconnectedFlight().with(singletonList(SECOND_BCN_MAD_FLIGHT)).build()
                )));
    }

    @Test
    public void getInterconnectedFlightWithOneStopRoute() {
        when(flightsRepository.searchBy(BCN_MAD_ROUTE, YEAR, MONTH))
                .thenReturn(asList(
                        FIRST_BCN_MAD_FLIGHT,
                        SECOND_BCN_MAD_FLIGHT
                ));
        when(flightsRepository.searchBy(MAD_LON_ROUTE, YEAR, MONTH))
                .thenReturn(asList(
                        MAD_LON_FLIGHT_INTERCONNECTED_WITH_SECOND_BCN_MAD_FLIGHT,
                        MAD_LON_FLIGHT_NOT_INTERCONNECTED
                ));

        List<InterconnectedFlight> interconnectedFlights = interconnectedFlightService
                .from(anInterconnectedRoute()
                                .with(asList(
                                        BCN_MAD_ROUTE,
                                        MAD_LON_ROUTE
                                )).build(),
                        YEAR,
                        MONTH);

        assertThat(interconnectedFlights,
                equalTo(singletonList(
                        anInterconnectedFlight()
                                .with(asList(
                                        SECOND_BCN_MAD_FLIGHT,
                                        MAD_LON_FLIGHT_INTERCONNECTED_WITH_SECOND_BCN_MAD_FLIGHT
                                )).build()
                )));
    }
}