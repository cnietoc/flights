package es.cnieto.flights.domain;

import es.cnieto.flights.domain.secondary.RoutesRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class InterconnectedRouteService {
    private final RoutesRepository routesRepository;

    // TODO test
    public List<InterconnectedRoute> from(String departureAirport, String arrivalAirport) {
        Routes routes = routesRepository.findAll();

        return routes.from(departureAirport)
                .stream()
                .map(route -> getLinkedRoutesFor(route, arrivalAirport, routes))
                .flatMap(List::stream)
                .collect(toList());
    }

    private List<InterconnectedRoute> getLinkedRoutesFor(Route firstRoute, String arrivalAirport, Routes allRoutes) {
        List<InterconnectedRoute> interconnectedRoutes = new ArrayList<>();
        if (firstRoute.getArrivalAirport().equals(arrivalAirport)) {
            interconnectedRoutes.add(new InterconnectedRoute(singletonList(firstRoute)));
        }
        List<Route> routesList = allRoutes.from(firstRoute.getArrivalAirport());
        for (Route route : routesList) {
            if (route.getArrivalAirport().equals(arrivalAirport)) {
                interconnectedRoutes.add(new InterconnectedRoute(asList(firstRoute, route)));
            }
        }
        return interconnectedRoutes;
    }
}
