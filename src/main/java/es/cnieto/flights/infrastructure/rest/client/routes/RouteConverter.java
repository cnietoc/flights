package es.cnieto.flights.infrastructure.rest.client.routes;

import es.cnieto.flights.domain.Route;

public class RouteConverter {
    public Route from(RouteRest routeRest) {
        return new Route(routeRest.getAirportFrom(), routeRest.getAirportTo());
    }
}
