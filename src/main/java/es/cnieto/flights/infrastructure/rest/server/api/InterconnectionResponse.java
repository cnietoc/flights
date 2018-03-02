package es.cnieto.flights.infrastructure.rest.server.api;

import lombok.Data;

@Data
public class InterconnectionResponse {
    private final int stops;
    private final LegsResponse legs;
}
