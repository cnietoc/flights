package es.cnieto.flights.domain.secondary;

import es.cnieto.flights.domain.Flight;

import java.time.Month;
import java.util.List;

public interface FlightsRepository {
    List<Flight> searchBy(String departureAirport, String arrivalAirport, int year, Month month);
}
