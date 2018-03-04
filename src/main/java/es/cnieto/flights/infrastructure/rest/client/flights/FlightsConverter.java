package es.cnieto.flights.infrastructure.rest.client.flights;

import es.cnieto.flights.domain.Flight;
import es.cnieto.flights.domain.Route;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FlightsConverter {
    public List<Flight> from(Route route, int year, MonthlyFlightsRest monthlyFlightsRest) {
        return from(route, year, monthlyFlightsRest.getMonth(), monthlyFlightsRest.getDays()).collect(toList());
    }

    private Stream<Flight> from(Route route, int year, int month, List<DailyFlightsRest> days) {
        return days.stream()
                .flatMap(day -> this.from(route,
                        year,
                        month,
                        day.getDay(),
                        day.getFlights()));
    }

    private Stream<Flight> from(Route route, int year, int month, int day, List<FlightRest> flights) {
        return flights.stream()
                .map(flight -> this.from(route,
                        year,
                        month,
                        day,
                        flight));
    }

    private Flight from(Route route, int year, int month, int day, FlightRest flight) {
        return new Flight(route,
                LocalDateTime.of(year, month, day, flight.getDepartureHour(), flight.getDepartureMinute()),
                LocalDateTime.of(year, month, day, flight.getArrivalHour(), flight.getArrivalMinute()));
    }
}
