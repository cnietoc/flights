package es.cnieto.flights.infrastructure.rest.server.api;

import lombok.Data;

import java.util.List;

@Data
public class LegsResponse {
    private final List<LegResponse> legs;
}
