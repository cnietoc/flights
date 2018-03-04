package es.cnieto.flights.infrastructure.rest.server;

import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import es.cnieto.flights.infrastructure.spring.FlightsApplication;
import org.json.JSONException;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static java.lang.ClassLoader.getSystemResource;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlightsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "service.endpoint=http://localhost:9889/",
})
public class InterconnectionsControllerIT {
    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(options()
            .usingFilesUnderDirectory("src/test/resources/interconnectionsControllerIT")
            .port(9889));

    @Rule
    public WireMockClassRule instanceRule = wireMockRule;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void returnInterconnectionsAsExpected() throws IOException, JSONException, URISyntaxException {
        String appResponse = testRestTemplate.getForObject("/interconnections?departure=DUB&arrival=WRO&departureDateTime=2018-03-29T07:00&arrivalDateTime=2018-03-29T23:30", String.class);

        assertEquals(expectedResponse(), appResponse, true);
    }

    private String expectedResponse() throws IOException, URISyntaxException {
        return new String(readAllBytes(get(getSystemResource("interconnectionsControllerIT/expectedResponse.json").toURI())));
    }
}