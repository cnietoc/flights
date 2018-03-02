package es.cnieto.flights.domain;

import java.time.LocalDateTime;

public final class FlightBuilder {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    private FlightBuilder() {
    }

    public static FlightBuilder aFlight() {
        return new FlightBuilder();
    }

    public FlightBuilder withDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
        return this;
    }

    public FlightBuilder withArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
        return this;
    }

    public FlightBuilder withDepartureDateTime(LocalDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
        return this;
    }

    public FlightBuilder withArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
        return this;
    }

    public Flight build() {
        return new Flight(departureAirport, arrivalAirport, departureDateTime, arrivalDateTime);
    }
}
