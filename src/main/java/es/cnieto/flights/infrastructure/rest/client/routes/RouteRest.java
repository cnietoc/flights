package es.cnieto.flights.infrastructure.rest.client.routes;

import lombok.Data;

@Data
class RouteRest {
    private String airportFrom;
    private String airportTo;
    private String connectingAirport;
}
