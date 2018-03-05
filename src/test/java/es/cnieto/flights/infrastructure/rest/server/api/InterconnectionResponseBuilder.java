package es.cnieto.flights.infrastructure.rest.server.api;

import java.util.List;

public final class InterconnectionResponseBuilder {
    private int stops;
    private List<LegResponse> legs;

    private InterconnectionResponseBuilder() {
    }

    public static InterconnectionResponseBuilder anInterconnectionResponse() {
        return new InterconnectionResponseBuilder();
    }

    public InterconnectionResponseBuilder withStops(int stops) {
        this.stops = stops;
        return this;
    }

    public InterconnectionResponseBuilder withLegs(List<LegResponse> legs) {
        this.legs = legs;
        return this;
    }

    public InterconnectionResponse build() {
        return new InterconnectionResponse(stops, legs);
    }
}
