package es.cnieto.flights.domain.secondary;

import es.cnieto.flights.domain.Routes;

public interface RoutesRepository {
    Routes findAll();
}
