package es.cnieto.flights.infrastructure.rest.server;

import es.cnieto.flights.domain.Interconnections;
import es.cnieto.flights.domain.primary.InterconnectionsService;
import es.cnieto.flights.infrastructure.rest.server.api.InterconnectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterconnectionsController {
    private final InterconnectionsService interconnectionsService;
    private final InterconnectionsResponseConverter interconnectionsResponseConverter;

    @RequestMapping("/interconnections")
    public List<InterconnectionResponse> interconnections(@RequestParam("departure") String departureAirport,
                                                          @RequestParam("arrival") String arrivalAirport,
                                                          @RequestParam("departureDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
                                                          @RequestParam("arrivalDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTime) {

        Interconnections interconnections = interconnectionsService
                .searchFor(departureAirport, departureDateTime, arrivalAirport, arrivalDateTime);

        return interconnectionsResponseConverter.from(interconnections);
    }
}
