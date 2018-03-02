package es.cnieto.flights.infrastructure.rest.server;

import es.cnieto.flights.domain.Flight;
import es.cnieto.flights.infrastructure.rest.server.api.LegResponse;

public class LegResponseConverter {
    public LegResponse from(Flight flight) {
        return new LegResponse(flight.getDepartureAirport(),
                flight.getArrivalAirport(),
                flight.getDepartureDateTime(),
                flight.getArrivalDateTime());
    }
}
