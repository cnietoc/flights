package es.cnieto.flights.domain;

import es.cnieto.flights.domain.secondary.RoutesRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static es.cnieto.flights.domain.InterconnectedRouteBuilder.anInterconnectedRoute;
import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static es.cnieto.flights.domain.RoutesBuilder.aRoutes;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InterconnectedRouteServiceTest {
    private static final String BCN_AIRPORT = "BCN";
    private static final String MAD_AIRPORT = "MAD";
    private static final String LON_AIRPORT = "LON";
    private static final String DUB_AIRPORT = "DUB";

    @Mock
    private RoutesRepository routesRepository;
    @InjectMocks
    private InterconnectedRouteService interconnectedRouteService;

    @Test
    public void getInterconnectedRoutes() {
        when(routesRepository.findAll()).thenReturn(aRoutes()
                .with(asList(
                        aRoute().from(BCN_AIRPORT).to(MAD_AIRPORT).build(),
                        aRoute().from(BCN_AIRPORT).to(DUB_AIRPORT).build(),
                        aRoute().from(DUB_AIRPORT).to(LON_AIRPORT).build(),
                        aRoute().from(BCN_AIRPORT).to(LON_AIRPORT).build(),
                        aRoute().from(LON_AIRPORT).to(MAD_AIRPORT).build()
                ))
                .build());

        List<InterconnectedRoute> interconnectedRoutes = interconnectedRouteService.from(BCN_AIRPORT, MAD_AIRPORT);

        assertThat(interconnectedRoutes,
                equalTo(asList(
                        anInterconnectedRoute()
                                .with(singletonList(
                                        aRoute().from(BCN_AIRPORT).to(MAD_AIRPORT).build()))
                                .build(),
                        anInterconnectedRoute()
                                .with(asList(
                                        aRoute().from(BCN_AIRPORT).to(LON_AIRPORT).build(),
                                        aRoute().from(LON_AIRPORT).to(MAD_AIRPORT).build()))
                                .build())));
    }

}