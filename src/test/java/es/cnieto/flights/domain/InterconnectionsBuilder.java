package es.cnieto.flights.domain;

import java.util.List;

public final class InterconnectionsBuilder {
    private List<InterconnectedFlight> interconnectedFlights;

    private InterconnectionsBuilder() {
    }

    public static InterconnectionsBuilder anInterconnections() {
        return new InterconnectionsBuilder();
    }

    public InterconnectionsBuilder with(List<InterconnectedFlight> interconnectedFlights) {
        this.interconnectedFlights = interconnectedFlights;
        return this;
    }

    public Interconnections build() {
        return new Interconnections(interconnectedFlights);
    }
}
