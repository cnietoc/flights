package es.cnieto.flights.infrastructure.rest.client.routes;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import es.cnieto.flights.domain.Routes;
import es.cnieto.flights.infrastructure.spring.FlightsApplication;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static es.cnieto.flights.domain.RouteBuilder.aRoute;
import static es.cnieto.flights.domain.RoutesBuilder.aRoutes;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlightsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = {
        "service.endpoint=http://localhost:9869/",
})
public class RestRoutesRepositoryIT {
    private static final String DUB_AIRPORT = "DUB";
    private static final String SXF_AIRPORT = "SXF";
    private static final String BVA_AIRPORT = "BVA";
    private static final String TFS_AIRPORT = "TFS";
    private static final String CIA_AIRPORT = "CIA";
    private static final String NRN_AIRPORT = "NRN";
    private static final String BDS_AIRPORT = "BDS";
    private static final String CAG_AIRPORT = "CAG";
    private static final String SVQ_AIRPORT = "SVQ";
    private static final String NAP_AIRPORT = "NAP";
    private static final String GRO_AIRPORT = "GRO";

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(options()
            .usingFilesUnderDirectory("src/test/resources/restRoutesRepositoryIT")
            .port(9869));

    @Rule
    public WireMockClassRule instanceRule = wireMockRule;

    @Autowired
    private RestRoutesRepository restRoutesRepository;

    @Test
    public void returnRoutesAsExpected() {
        Routes routes = restRoutesRepository.findAll();

        assertThat(routes,
                equalTo(aRoutes()
                        .with(asList(
                                aRoute().from(DUB_AIRPORT)
                                        .to(SXF_AIRPORT)
                                        .build(),
                                aRoute().from(BVA_AIRPORT)
                                        .to(TFS_AIRPORT)
                                        .build(),
                                aRoute().from(CIA_AIRPORT)
                                        .to(NRN_AIRPORT)
                                        .build(),
                                aRoute().from(BDS_AIRPORT)
                                        .to(CAG_AIRPORT)
                                        .build(),
                                aRoute().from(SVQ_AIRPORT)
                                        .to(NAP_AIRPORT)
                                        .build(),
                                aRoute().from(GRO_AIRPORT)
                                        .to(BVA_AIRPORT)
                                        .build()))
                        .build()));
    }
}