package es.cnieto.flights.infrastructure.rest.client.flights;

import es.cnieto.flights.domain.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FlightsConverter {
    public List<Flight> from(String departureAirport, String arrivalAirport, int year, MonthlyFlightsRest monthlyFlightsRest) {
        return from(departureAirport, arrivalAirport, year, monthlyFlightsRest.getMonth(), monthlyFlightsRest.getDays()).collect(toList());
    }

    private Stream<Flight> from(String departureAirport, String arrivalAirport, int year, int month, List<DailyFlightsRest> days) {
        return days.stream()
                .flatMap(day -> this.from(departureAirport,
                        arrivalAirport,
                        year,
                        month,
                        day.getDay(),
                        day.getFlights()));
    }

    private Stream<Flight> from(String departureAirport, String arrivalAirport, int year, int month, int day, List<FlightRest> flights) {
        return flights.stream()
                .map(flight -> this.from(departureAirport,
                        arrivalAirport,
                        year,
                        month,
                        day,
                        flight));
    }

    private Flight from(String departureAirport, String arrivalAirport, int year, int month, int day, FlightRest flight) {
        return new Flight(departureAirport,
                arrivalAirport,
                LocalDateTime.of(year, month, day, flight.getDepartureHour(), flight.getDepartureMinute()),
                LocalDateTime.of(year, month, day, flight.getArrivalHour(), flight.getArrivalMinute()));
    }
}
