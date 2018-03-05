package es.cnieto.flights.infrastructure.rest.server.api;

import java.time.LocalDateTime;

public final class LegResponseBuilder {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    private LegResponseBuilder() {
    }

    public static LegResponseBuilder aLegResponse() {
        return new LegResponseBuilder();
    }

    public LegResponseBuilder withDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
        return this;
    }

    public LegResponseBuilder withArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
        return this;
    }

    public LegResponseBuilder withDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
        return this;
    }

    public LegResponseBuilder withArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
        return this;
    }

    public LegResponse build() {
        return new LegResponse(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime);
    }
}
