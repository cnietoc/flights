package es.cnieto.flights.domain;

import lombok.Data;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
public class Interconnections {
    private final List<DirectInterconnection> directInterconnection;

    public List<Flight> getDirectFlights() {
        return directInterconnection
                .stream()
                .map(DirectInterconnection::getFlight)
                .collect(toList());
    }
}
