package es.cnieto.flights.infrastructure.rest.client.flights;

import lombok.Data;

import java.util.List;

@Data
class DailyFlightsRest {
    int day;
    List<FlightRest> flights;
}
