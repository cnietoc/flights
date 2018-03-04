package es.cnieto.flights.infrastructure.rest.client.routes;

import es.cnieto.flights.domain.Routes;
import es.cnieto.flights.domain.secondary.RoutesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class RestRoutesRepository implements RoutesRepository {
    private final RestTemplate restTemplate;
    private final URI serviceEndPoint;
    private final RouteConverter routeConverter;

    @Override
    public Routes findAll() {
        RouteRest[] routesRest = restTemplate.getForObject(serviceEndPoint.resolve("core/3/routes"), RouteRest[].class);
        return new Routes(Stream.of(routesRest)
                .filter(routeRest -> routeRest.getConnectingAirport() == null)
                .map(routeConverter::from)
                .collect(toList()));
    }
}
