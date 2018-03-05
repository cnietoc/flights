package es.cnieto.flights.domain;

public final class RouteBuilder {
    private String departureAirport;
    private String arrivalAirport;

    private RouteBuilder() {
    }

    public static RouteBuilder aRoute() {
        return new RouteBuilder();
    }

    public RouteBuilder from(String departureAirport) {
        this.departureAirport = departureAirport;
        return this;
    }

    public RouteBuilder to(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
        return this;
    }

    public Route build() {
        return new Route(departureAirport, arrivalAirport);
    }
}
