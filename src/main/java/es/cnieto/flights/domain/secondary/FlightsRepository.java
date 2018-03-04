package es.cnieto.flights.domain.secondary;

import es.cnieto.flights.domain.Flight;
import es.cnieto.flights.domain.Route;

import java.time.Month;
import java.util.List;

public interface FlightsRepository {
    List<Flight> searchBy(Route route, int year, Month month);
}
