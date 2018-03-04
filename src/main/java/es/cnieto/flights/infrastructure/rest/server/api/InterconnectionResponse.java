package es.cnieto.flights.infrastructure.rest.server.api;

import lombok.Data;

import java.util.List;

@Data
public class InterconnectionResponse {
    private final int stops;
    private final List<LegResponse> legs;
}
