package es.cnieto.flights.infrastructure.rest.server;

import es.cnieto.flights.domain.Flight;
import es.cnieto.flights.domain.Interconnections;
import es.cnieto.flights.infrastructure.rest.server.api.InterconnectionResponse;
import es.cnieto.flights.infrastructure.rest.server.api.LegsResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
// TODO test
public class InterconnectionsResponseConverter {
    private final LegResponseConverter legResponseConverter;

    public List<InterconnectionResponse> from(Interconnections interconnections) {
        Optional<InterconnectionResponse> directInterconnection = getDirectInterconnectionsFrom(interconnections.getDirectFlights());

        return Stream.of(directInterconnection)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Optional<InterconnectionResponse> getDirectInterconnectionsFrom(List<Flight> directFlights) {
        InterconnectionResponse interconnectionResponse = null;
        if (!directFlights.isEmpty()) {
            interconnectionResponse = new InterconnectionResponse(0,
                    new LegsResponse(directFlights.stream()
                            .map(legResponseConverter::from)
                            .collect(toList())));
        }

        return Optional.ofNullable(interconnectionResponse);
    }
}
