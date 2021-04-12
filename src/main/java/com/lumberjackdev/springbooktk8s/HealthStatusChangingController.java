package com.lumberjackdev.springbooktk8s;

import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthStatusChangingController {

    private final ConfigurableHealthContributor configurableHealthContributor;

    public HealthStatusChangingController(ConfigurableHealthContributor configurableHealthContributor) {
        this.configurableHealthContributor = configurableHealthContributor;
    }

    @GetMapping("/simulate-unhealthy")
    public Mono<Void> simulateUnhealthy() {
        configurableHealthContributor.setHealth(Health.down().build());
        return Mono.empty();
    }
}
