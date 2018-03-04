package es.cnieto.flights.infrastructure.rest.server;

import es.cnieto.flights.domain.Interconnections;
import es.cnieto.flights.infrastructure.rest.server.api.InterconnectionResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
// TODO test
public class InterconnectionsResponseConverter {
    private final LegResponseConverter legResponseConverter;

    public List<InterconnectionResponse> from(Interconnections interconnections) {
        return interconnections.getInterconnectedFlights()
                .stream()
                .map(linkedFlights ->
                        new InterconnectionResponse(linkedFlights.getStops(),
                                linkedFlights.getFlights()
                                        .stream()
                                        .map(legResponseConverter::from)
                                        .collect(toList())))
                .collect(toList());
    }
}
