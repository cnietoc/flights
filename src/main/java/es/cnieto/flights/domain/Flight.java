package es.cnieto.flights.domain;

import lombok.Data;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;


@Data
public class Flight {
    private final Route route;
    private final LocalDateTime departureDateTime;
    private final LocalDateTime arrivalDateTime;

    public boolean departureIsAfter(LocalDateTime dateTime) {
        return departureDateTime.isAfter(dateTime);
    }

    public boolean arrivalIsBefore(LocalDateTime dateTime) {
        return arrivalDateTime.isBefore(dateTime);
    }

    public boolean departureFrom(String airport) {
        return route.departureFrom(airport);
    }

    public boolean arriveTo(String airport) {
        return route.arriveTo(airport);
    }

    public boolean isInterconnectedWith(Flight flight) {
        return route.isInterconnectedWith(flight.route)
                && arrivalIsBefore(flight.departureDateTime)
                && arrivalDateTime.until(flight.departureDateTime, HOURS) > 2;
    }

    public String getDepartureAirport() {
        return route.getDepartureAirport();
    }

    public String getArrivalAirport() {
        return route.getArrivalAirport();
    }
}
