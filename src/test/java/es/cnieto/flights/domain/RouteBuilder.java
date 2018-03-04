package es.cnieto.flights.domain;

public final class RouteBuilder {
    private String departureAirport;
    private String arrivalAirport;

    private RouteBuilder() {
    }

    public static RouteBuilder aRoute() {
        return new RouteBuilder();
    }

    public RouteBuilder withDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
        return this;
    }

    public RouteBuilder withArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
        return this;
    }

    public Route build() {
        return new Route(departureAirport, arrivalAirport);
    }
}
