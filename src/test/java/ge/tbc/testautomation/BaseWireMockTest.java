package ge.tbc.testautomation;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseWireMockTest {
    protected WireMockServer wireMockServer;

    @BeforeClass(alwaysRun = true)
    public void setUpWireMock() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        configureFor("localhost", 8080);

        setupSwoopStub();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownWireMock() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    private void setupSwoopStub() {
        stubFor(get(urlPathEqualTo("/api/search"))
                .withQueryParam("filter[cat_id]", matching("\\d+"))
                .withQueryParam("LangID", matching("\\d+"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("swoopResponse.json")
                )
        );
    }
}