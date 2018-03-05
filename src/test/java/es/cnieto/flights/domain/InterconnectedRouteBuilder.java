package es.cnieto.flights.domain;

import java.util.List;

public final class InterconnectedRouteBuilder {
    private List<Route> routes;

    private InterconnectedRouteBuilder() {
    }

    public static InterconnectedRouteBuilder anInterconnectedRoute() {
        return new InterconnectedRouteBuilder();
    }

    public InterconnectedRouteBuilder with(List<Route> routes) {
        this.routes = routes;
        return this;
    }

    public InterconnectedRoute build() {
        return new InterconnectedRoute(routes);
    }
}
