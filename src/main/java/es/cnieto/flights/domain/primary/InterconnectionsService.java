package es.cnieto.flights.domain.primary;

import es.cnieto.flights.domain.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class InterconnectionsService {
    private final InterconnectedRouteService interconnectedRouteService;
    private final InterconnectedFlightService interconnectedFlightService;

    public Interconnections searchFor(String departureAirport,
                                      LocalDateTime departureDateTime,
                                      String arrivalAirport,
                                      LocalDateTime arrivalDateTime) {
        List<InterconnectedRoute> interconnectedRoutes = interconnectedRouteService.from(departureAirport, arrivalAirport);
        List<InterconnectedFlight> interconnectedFlights = interconnectedRoutes.stream()
                .map(interconnectedRoute -> interconnectedFlightService.from(interconnectedRoute, departureDateTime.getYear(), departureDateTime.getMonth()))
                .flatMap(List::stream)
                .collect(toList());

        return new Interconnections(interconnectedFlights.stream()
                .filter(interconnectedFlight -> interconnectedFlight.departureIsAfter(departureDateTime))
                .filter(interconnectedFlight -> interconnectedFlight.arrivalIsBefore(arrivalDateTime))
                .collect(toList()));
    }
}
