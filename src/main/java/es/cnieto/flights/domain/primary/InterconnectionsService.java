package es.cnieto.flights.domain.primary;

import es.cnieto.flights.domain.DirectInterconnection;
import es.cnieto.flights.domain.Flight;
import es.cnieto.flights.domain.Interconnections;
import es.cnieto.flights.domain.secondary.FlightsRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// TODO test
@RequiredArgsConstructor
public class InterconnectionsService {
    private final FlightsRepository flightsRepository;

    public Interconnections searchFor(String departureAirport,
                                      LocalDateTime departureDateTime,
                                      String arrivalAirport,
                                      LocalDateTime arrivalDateTime) {
        List<Flight> directFlights = getDirectFlights(departureAirport, departureDateTime, arrivalAirport);

        return new Interconnections(directFlights.stream()
                .filter(flight -> flight.departureIsAfter(departureDateTime))
                .filter(flight -> flight.arrivalIsBefore(arrivalDateTime))
                .map(DirectInterconnection::new)
                .collect(Collectors.toList()));
    }

    private List<Flight> getDirectFlights(String departureAirport,
                                          LocalDateTime departureDateTime,
                                          String arrivalAirport) {
        return flightsRepository.searchBy(departureAirport,
                arrivalAirport,
                departureDateTime.getYear(),
                departureDateTime.getMonth());
    }
}
