package es.cnieto.flights.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Flight {
    private final String departureAirport;
    private final String arrivalAirport;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;

    public boolean departureIsAfter(LocalDateTime dateTime) {
        return departureDateTime.isAfter(dateTime);
    }

    public boolean arrivalIsBefore(LocalDateTime dateTime) {
        return arrivalDateTime.isBefore(dateTime);
    }
}
