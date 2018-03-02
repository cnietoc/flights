package es.cnieto.flights.infrastructure.rest.server.api;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LegResponse {
    private final String departureAirport;
    private final String arrivalAirport;

    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;
}
