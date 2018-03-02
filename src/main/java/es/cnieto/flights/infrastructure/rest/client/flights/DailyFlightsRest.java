package es.cnieto.flights.infrastructure.rest.client.flights;

import lombok.Data;

import java.util.List;

@Data
public class DailyFlightsRest {
    int day;
    List<FlightRest> flights;
}
