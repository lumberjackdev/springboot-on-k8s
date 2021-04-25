package com.lumberjackdev.springbooktk8s;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HealthStatusChangingController {
    private final ConfigurableHealthContributor configurableHealthContributor;

    public HealthStatusChangingController(ConfigurableHealthContributor configurableHealthContributor) {
        this.configurableHealthContributor = configurableHealthContributor;
    }

    @GetMapping("/unhealthy")
    public Mono<String> makeApplicationUnhealthy() {
        configurableHealthContributor.setUnhealthy();
        return Mono.just("Status Changed to Unhealthy");
    }

    @GetMapping("/healthy")
    public Mono<String> makeApplicationHealthy() {
        configurableHealthContributor.setHealthy();
        return Mono.just("Status Changed to Healthy");
    }
}
