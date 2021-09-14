package com.airfrance.technicaltest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.PostConstruct;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.yml")
class TechnicalTestApplicationTests {

    @LocalServerPort
    private int port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    protected TestRestTemplate restTemplate;

    @PostConstruct
    public void init() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .rootUri(String.format("http://localhost:%d%s", port, contextPath))
                .setReadTimeout(Duration.ofMillis(60000L));
        restTemplate = new TestRestTemplate(restTemplateBuilder, null, null);
    }

    private HttpHeaders headersWithoutAuth() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    protected HttpEntity<String> emptyPayloadWithoutAuth() {
        return new HttpEntity<>(headersWithoutAuth());
    }

    protected HttpEntity<?> payloadWithoutAuth(Object body) {
        return new HttpEntity<>(body, headersWithoutAuth());
    }


}
