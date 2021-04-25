package com.lumberjackdev.springbooktk8s;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthStatusChangingTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void makeApplicationUnhealthy() {
        webTestClient.get().uri("/unhealthy").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo("Status Changed to Unhealthy");
        webTestClient.get().uri("/actuator/health").exchange().expectStatus().is5xxServerError().expectBody(String.class);
    }

    @Test
    void makeApplicationHealthy() {
        webTestClient.get().uri("/unhealthy").exchange().expectStatus().isOk();
        webTestClient.get().uri("/actuator/health").exchange().expectStatus().is5xxServerError();

        webTestClient.get().uri("/healthy").exchange().expectStatus().isOk();
        webTestClient.get().uri("/actuator/health").exchange().expectStatus().isOk();
    }
}