package es.cnieto.flights.domain;

import java.util.List;

public final class InterconnectedFlightBuilder {
    private List<Flight> flights;

    private InterconnectedFlightBuilder() {
    }

    public static InterconnectedFlightBuilder anInterconnectedFlight() {
        return new InterconnectedFlightBuilder();
    }

    public InterconnectedFlightBuilder with(List<Flight> flights) {
        this.flights = flights;
        return this;
    }

    public InterconnectedFlight build() {
        return new InterconnectedFlight(flights);
    }
}
