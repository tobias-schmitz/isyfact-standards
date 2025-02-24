package de.bund.bva.isyfact.security.authentication;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import de.bund.bva.isyfact.security.AbstractOidcProviderTest;
import de.bund.bva.isyfact.security.example.IsySecurityTestApplication;
import de.bund.bva.isyfact.security.example.config.OAuth2WebClientConfiguration;
import de.bund.bva.isyfact.security.example.config.SecurityConfig;
import de.bund.bva.isyfact.security.example.rest.ExampleRestController;

import reactor.core.publisher.Mono;

@ActiveProfiles(profiles = "test-clients")
@SpringBootTest(classes = {
        IsySecurityTestApplication.class, SecurityConfig.class, ExampleRestController.class, OAuth2WebClientConfiguration.class
}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// clear context so WebClient will fetch a fresh access token
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientCredentialsAuthenticationTest extends AbstractOidcProviderTest {

    @LocalServerPort
    private int port;

    /** WebClient configured with the ServletOAuth2AuthorizedClientExchangeFilterFunction. */
    @Autowired
    private WebClient webClient;

    private String pingUri;

    @BeforeEach
    public void setup() {
        embeddedOidcProvider.removeAllClients();
        pingUri = "http://localhost:" + port + "/ping";
    }

    @Test
    public void shouldAllowPingFromClientWithRole() {
        embeddedOidcProvider.addClient("client-credentials-test-client", "supersecretpassword", Collections.singleton("Rolle_A"));

        String body = webClient.get().uri(pingUri).exchangeToMono(response -> {
            assertEquals(HttpStatus.OK, response.statusCode());
            return response.bodyToMono(String.class);
        }).block();

        assertEquals("true", body);
    }

    @Test
    public void shouldDenyPingFromClientWithoutRole() {
        embeddedOidcProvider.addClient("client-credentials-test-client", "supersecretpassword", Collections.emptySet());

        HttpStatus status = webClient.get().uri(pingUri)
                .exchangeToMono(response -> Mono.just(response.statusCode()))
                .block();

        assertEquals(HttpStatus.FORBIDDEN, status);
    }

    @Test
    public void shouldDenyPingWithoutClient() {
        // create new WebClient without the ServletOAuth2AuthorizedClientExchangeFilterFunction
        HttpStatus status = WebClient.create().get().uri(pingUri)
                .exchangeToMono(response -> Mono.just(response.statusCode()))
                .block();

        assertEquals(HttpStatus.UNAUTHORIZED, status);
    }

}
