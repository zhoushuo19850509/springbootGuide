package com.example.gatewayservice.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.assertj.core.api.Assertions.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"httpbin=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port=0)
public class ApplicationTest {

    private WebTestClient webTestCLient;

    @Test
    public void contextLoads(){
        stubFor(get(urlEqualTo("/get")).
                willReturn(aResponse()
                        .withBody("{\"headers\": {\"Hello\": \"World\"}}")
                        .withHeader("Content-Type","application/json")));

        stubFor(get(urlEqualTo("/delay/3")).
                willReturn(aResponse().
                        withBody("fallback...").
                        withFixedDelay(3000)));

        webTestCLient
                .get().uri("/get")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.headers.Hello").isEqualTo("World");

        webTestCLient
                .get().uri("/delay/3")
                .header("Host","www.hystrix.com")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        assertThat(response.getResponseBody()).isEqualTo("fallback".getBytes()));
    }


}
