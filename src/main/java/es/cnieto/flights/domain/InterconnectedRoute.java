package es.cnieto.flights.domain;

import lombok.Data;

import java.util.List;

@Data
public class InterconnectedRoute {
    private final List<Route> routes;

    public boolean departureFrom(String airport) {
        return getFirstRoute().departureFrom(airport);
    }

    private Route getFirstRoute() {
        return routes.get(0);
    }
}
