package es.cnieto.flights.infrastructure.rest.client.flights;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import es.cnieto.flights.domain.Flight;
import es.cnieto.flights.infrastructure.spring.FlightsApplication;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static es.cnieto.flights.domain.FlightBuilder.aFlight;
import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlightsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
        "service.endpoint=http://localhost:9899/",
})
public class RestFlightsRepositoryIT {
    private static final String DUB_AIRPORT = "DUB";
    private static final String WRO_AIRPORT = "WRO";
    private static final int YEAR = 2018;
    private static final Month MONTH = Month.APRIL;
    private static final String BCN_AIRPORT = "BCN";

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(options()
            .usingFilesUnderDirectory("src/test/resources/restFlightsRepositoryIT")
            .port(9899));

    @Rule
    public WireMockClassRule instanceRule = wireMockRule;

    @Autowired
    private RestFlightsRepository restFlightsRepository;

    @Test
    public void returnFlightsAsExpected() {
        List<Flight> flights = restFlightsRepository
                .searchBy(
                        aRoute().withDepartureAirport(DUB_AIRPORT)
                                .withArrivalAirport(WRO_AIRPORT)
                                .build(),
                        YEAR,
                        MONTH);

        assertThat(flights, equalTo(
                asList(aFlight()
                                .withRoute(aRoute().withDepartureAirport(DUB_AIRPORT)
                                        .withArrivalAirport(WRO_AIRPORT)
                                        .build())
                                .withDepartureDateTime(LocalDateTime.of(YEAR, MONTH.getValue(), 1, 17, 25))
                                .withArrivalDateTime(LocalDateTime.of(YEAR, MONTH.getValue(), 1, 21, 0))
                                .build(),
                        aFlight()
                                .withRoute(aRoute().withDepartureAirport(DUB_AIRPORT)
                                        .withArrivalAirport(WRO_AIRPORT)
                                        .build())
                                .withDepartureDateTime(LocalDateTime.of(YEAR, MONTH.getValue(), 30, 9, 45))
                                .withArrivalDateTime(LocalDateTime.of(YEAR, MONTH.getValue(), 30, 13, 20))
                                .build())));
    }

    @Test
    public void returnEmptyListWhenNoFlights() {
        List<Flight> flights = restFlightsRepository
                .searchBy(
                        aRoute().withDepartureAirport(BCN_AIRPORT)
                                .withArrivalAirport(WRO_AIRPORT)
                                .build(),
                        YEAR,
                        MONTH);

        assertThat(flights, is(emptyList()));
    }

}