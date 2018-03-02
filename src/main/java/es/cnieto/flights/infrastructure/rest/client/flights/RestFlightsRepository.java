package es.cnieto.flights.infrastructure.rest.client.flights;

import es.cnieto.flights.domain.Flight;
import es.cnieto.flights.domain.secondary.FlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Month;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
public class RestFlightsRepository implements FlightsRepository {
    private final RestTemplate restTemplate;
    private final URI serviceEndPoint;
    private final FlightsConverter flightsConverter;

    @Override
    public List<Flight> searchBy(String departureAirport, String arrivalAirport, int year, Month month) {
        URI uri = getUriWithParams(departureAirport, arrivalAirport, year, month);

        try {
            MonthlyFlightsRest monthlyFlightsRest = restTemplate.getForObject(uri, MonthlyFlightsRest.class);
            return flightsConverter.from(departureAirport, arrivalAirport, year, monthlyFlightsRest);
        } catch (HttpClientErrorException httpClientErrorException) {
            if (NOT_FOUND.equals(httpClientErrorException.getStatusCode())) {
                return Collections.emptyList();
            } else {
                throw httpClientErrorException;
            }
        }
    }

    private URI getUriWithParams(String departureIata, String arrivalIata, int year, Month month) {
        Map<String, Object> params = new HashMap<>();
        params.put("departure", departureIata);
        params.put("arrival", arrivalIata);
        params.put("year", year);
        params.put("month", month.getValue());
        UriComponents builder = UriComponentsBuilder.fromUri(serviceEndPoint)
                .path("/timetable/3/schedules/")
                .path("/{departure}/")
                .path("/{arrival}/")
                .path("years/{year}/")
                .path("/months/{month}").buildAndExpand(params);
        return builder.toUri();
    }
}
