package es.cnieto.flights.domain;

import java.util.List;

public final class RoutesBuilder {
    private List<Route> allRoutes;

    private RoutesBuilder() {
    }

    public static RoutesBuilder aRoutes() {
        return new RoutesBuilder();
    }

    public RoutesBuilder with(List<Route> allRoutes) {
        this.allRoutes = allRoutes;
        return this;
    }

    public Routes build() {
        return new Routes(allRoutes);
    }
}
