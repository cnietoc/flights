package es.cnieto.flights.domain;

import lombok.Data;

import java.util.List;

@Data
public class Interconnections {
    private final List<InterconnectedFlight> interconnectedFlights;
}
