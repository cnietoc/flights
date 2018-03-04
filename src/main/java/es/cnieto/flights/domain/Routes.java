package es.cnieto.flights.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Routes {
    private final List<Route> allRoutes;

    public List<Route> from(String departureAirport) {
        return allRoutes.stream()
                .filter(route -> route.getDepartureAirport().equals(departureAirport))
                .collect(toList());
    }
}
