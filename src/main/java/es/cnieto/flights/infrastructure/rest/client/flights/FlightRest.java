package es.cnieto.flights.infrastructure.rest.client.flights;

import lombok.Data;

@Data
class FlightRest {
    private static final String TIME_SEPARATOR = ":";
    private String number;
    private String departureTime;
    private String arrivalTime;

    public int getDepartureHour() {
        return getHourFrom(departureTime);
    }

    public int getDepartureMinute() {
        return getMinuteFrom(departureTime);
    }

    public int getArrivalHour() {
        return getHourFrom(arrivalTime);
    }

    public int getArrivalMinute() {
        return getMinuteFrom(arrivalTime);
    }

    private int getHourFrom(String time) {
        return Integer.parseInt(time.split(TIME_SEPARATOR)[0]);
    }

    private int getMinuteFrom(String time) {
        return Integer.parseInt(time.split(TIME_SEPARATOR)[1]);
    }
}
