package es.cnieto.flights.domain;

import es.cnieto.flights.domain.secondary.FlightsRepository;
import lombok.RequiredArgsConstructor;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

@RequiredArgsConstructor
public class InterconnectedFlightService {
    private final FlightsRepository flightsRepository;

    // TODO test
    public List<InterconnectedFlight> from(InterconnectedRoute interconnectedRoute, int year, Month month) {
        List<InterconnectedFlight> interconnectedFlights = emptyList();

        for (Route route : interconnectedRoute.getRoutes()) {
            List<Flight> flights = getFlights(route, year, month);

            List<InterconnectedFlight> incompleteInterconnectedFlights = interconnectedFlights;
            interconnectedFlights = new ArrayList<>();

            for (Flight flight : flights) {
                if (interconnectedRoute.departureFrom(flight.getDepartureAirport())) {
                    interconnectedFlights.add(new InterconnectedFlight(flight));
                }

                for (InterconnectedFlight interconnectedFlight : incompleteInterconnectedFlights) {
                    if (interconnectedFlight.isInterconnectedWith(flight)) {
                        interconnectedFlights.add(interconnectedFlight.with(flight));
                    }
                }
            }
        }

        return interconnectedFlights;
    }

    private List<Flight> getFlights(Route route, int year, Month month) {
        return flightsRepository.searchBy(route, year, month);
    }
}
