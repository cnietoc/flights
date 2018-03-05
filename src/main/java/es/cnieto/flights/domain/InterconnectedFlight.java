package es.cnieto.flights.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static java.util.Collections.unmodifiableList;

@Data
public class InterconnectedFlight {
    private final List<Flight> flights;

    InterconnectedFlight(Flight flight) {
        this(singletonList(flight));
    }

    InterconnectedFlight(List<Flight> flights) {
        this.flights = unmodifiableList(flights);
    }

    public boolean isInterconnectedWith(Flight flight) {
        return getLastFlight().isInterconnectedWith(flight);
    }

    public boolean departureIsAfter(LocalDateTime departureDateTime) {
        return getFirstFlight().departureIsAfter(departureDateTime);
    }

    public boolean arrivalIsBefore(LocalDateTime arrivalDateTime) {
        return getLastFlight().arrivalIsBefore(arrivalDateTime);
    }

    public int getStops() {
        return flights.size() - 1;
    }

    public InterconnectedFlight with(Flight flight) {
        List<Flight> newFlights = new ArrayList<>(flights);
        newFlights.add(flight);
        return new InterconnectedFlight(newFlights);
    }

    private Flight getFirstFlight() {
        return flights.get(0);
    }

    private Flight getLastFlight() {
        return flights.get(flights.size() - 1);
    }
}
