package es.cnieto.flights.domain;

import lombok.Data;

@Data
public class Route {
    private final String departureAirport;
    private final String arrivalAirport;

    public boolean departureFrom(String airport) {
        return departureAirport.equals(airport);
    }

    public boolean arriveTo(String airport) {
        return arrivalAirport.equals(airport);
    }

    public boolean isInterconnectedWith(Route route) {
        return arriveTo(route.departureAirport);
    }
}
