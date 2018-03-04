package es.cnieto.flights.domain;

import java.time.LocalDateTime;

public final class FlightBuilder {
    private Route route;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;

    private FlightBuilder() {
    }

    public static FlightBuilder aFlight() {
        return new FlightBuilder();
    }

    public FlightBuilder withRoute(Route route) {
        this.route = route;
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
        return new Flight(route, departureDateTime, arrivalDateTime);
    }
}
