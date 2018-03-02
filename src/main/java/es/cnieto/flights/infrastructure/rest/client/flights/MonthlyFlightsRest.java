package es.cnieto.flights.infrastructure.rest.client.flights;

import lombok.Data;

import java.util.List;

@Data
public class MonthlyFlightsRest {
    private int month;
    private List<DailyFlightsRest> days;
}
