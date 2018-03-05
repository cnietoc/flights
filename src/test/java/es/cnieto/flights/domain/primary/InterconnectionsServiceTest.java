package es.cnieto.flights.domain.primary;

import es.cnieto.flights.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.Month;

import static es.cnieto.flights.domain.FlightBuilder.aFlight;
import static es.cnieto.flights.domain.InterconnectedFlightBuilder.anInterconnectedFlight;
import static es.cnieto.flights.domain.InterconnectedRouteBuilder.anInterconnectedRoute;
import static es.cnieto.flights.domain.InterconnectionsBuilder.anInterconnections;
import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectionsServiceTest {
    private static final String MAD_AIRPORT = "MAD";
    private static final String BCN_AIRPORT = "BCN";
    private static final String LON_AIRPORT = "LON";

    private static final Route MAD_BCN_ROUTE = aRoute().from(MAD_AIRPORT).to(BCN_AIRPORT).build();
    private static final Route LON_BCN_ROUTE = aRoute().from(LON_AIRPORT).to(BCN_AIRPORT).build();
    private static final Route MAD_LON_ROUTE = aRoute().from(MAD_AIRPORT).to(LON_AIRPORT).build();

    private static final InterconnectedRoute DIRECT_ROUTE_MAD_BCN = anInterconnectedRoute()
            .with(singletonList(
                    MAD_BCN_ROUTE
            )).build();
    private static final InterconnectedRoute INTERCONNECTED_ROUTE_MAD_LON_BCN = anInterconnectedRoute()
            .with(asList(
                    MAD_LON_ROUTE,
                    LON_BCN_ROUTE
            )).build();

    private static final int YEAR = 2018;
    private static final Month MONTH = Month.APRIL;

    private static final LocalDateTime DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 20, 7, 0);
    private static final LocalDateTime ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 23, 23, 30);

    private static final LocalDateTime DIRECT_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 21, 7, 0);
    private static final LocalDateTime DIRECT_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 21, 23, 30);

    private static final LocalDateTime MAD_LON_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 20, 23, 30);
    private static final LocalDateTime MAD_LON_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 21, 1, 15);
    private static final LocalDateTime LON_BCN_FLIGHT_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 22, 20, 30);
    private static final LocalDateTime LON_BCN_FLIGHT_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 23, 22, 30);

    private static final LocalDateTime BEFORE_DEPARTURE_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 19, 23, 30);
    private static final LocalDateTime AFTER_ARRIVAL_DATE_TIME = LocalDateTime.of(YEAR, MONTH.getValue(), 24, 23, 30);

    @Mock
    private InterconnectedRouteService interconnectedRouteService;
    @Mock
    private InterconnectedFlightService interconnectedFlightService;
    @InjectMocks
    private InterconnectionsService interconnectionsService;

    @Test
    public void searchInterconnections() {
        when(interconnectedRouteService.from(MAD_AIRPORT, BCN_AIRPORT))
                .thenReturn(asList(
                        DIRECT_ROUTE_MAD_BCN,
                        INTERCONNECTED_ROUTE_MAD_LON_BCN
                ));

        when(interconnectedFlightService.from(DIRECT_ROUTE_MAD_BCN, YEAR, MONTH))
                .thenReturn(asList(
                        directFlightWithDates(DIRECT_FLIGHT_DEPARTURE_DATE_TIME, DIRECT_FLIGHT_ARRIVAL_DATE_TIME),
                        directFlightWithDates(BEFORE_DEPARTURE_DATE_TIME, DIRECT_FLIGHT_ARRIVAL_DATE_TIME),
                        directFlightWithDates(DIRECT_FLIGHT_DEPARTURE_DATE_TIME, AFTER_ARRIVAL_DATE_TIME),
                        interconnectedFlightWithDates(MAD_LON_FLIGHT_DEPARTURE_DATE_TIME, LON_BCN_FLIGHT_ARRIVAL_DATE_TIME),
                        interconnectedFlightWithDates(BEFORE_DEPARTURE_DATE_TIME, LON_BCN_FLIGHT_ARRIVAL_DATE_TIME),
                        interconnectedFlightWithDates(MAD_LON_FLIGHT_DEPARTURE_DATE_TIME, AFTER_ARRIVAL_DATE_TIME)
                ));

        Interconnections interconnections = interconnectionsService.searchFor(MAD_AIRPORT, DEPARTURE_DATE_TIME, BCN_AIRPORT, ARRIVAL_DATE_TIME);

        assertThat(interconnections,
                equalTo(anInterconnections()
                        .with(asList(
                                directFlightWithDates(DIRECT_FLIGHT_DEPARTURE_DATE_TIME, DIRECT_FLIGHT_ARRIVAL_DATE_TIME),
                                interconnectedFlightWithDates(MAD_LON_FLIGHT_DEPARTURE_DATE_TIME, LON_BCN_FLIGHT_ARRIVAL_DATE_TIME)
                        ))
                        .build()));
    }

    private InterconnectedFlight directFlightWithDates(LocalDateTime directFlightDepartureDateTime, LocalDateTime directFlightArrivalDateTime) {
        return anInterconnectedFlight()
                .with(singletonList(
                        aFlight().withRoute(MAD_BCN_ROUTE)
                                .withDepartureDateTime(directFlightDepartureDateTime)
                                .withArrivalDateTime(directFlightArrivalDateTime)
                                .build()
                ))
                .build();
    }

    private InterconnectedFlight interconnectedFlightWithDates(LocalDateTime madLonFlightDepartureDateTime, LocalDateTime lonBcnFlightArrivalDateTime) {
        return anInterconnectedFlight()
                .with(asList(
                        aFlight().withRoute(MAD_LON_ROUTE)
                                .withDepartureDateTime(madLonFlightDepartureDateTime)
                                .withArrivalDateTime(MAD_LON_FLIGHT_ARRIVAL_DATE_TIME)
                                .build(),
                        aFlight().withRoute(LON_BCN_ROUTE)
                                .withDepartureDateTime(LON_BCN_FLIGHT_DEPARTURE_DATE_TIME)
                                .withArrivalDateTime(lonBcnFlightArrivalDateTime)
                                .build()
                ))
                .build();
    }
}