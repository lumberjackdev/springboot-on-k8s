package com.lumberjackdev.springbooktk8s;

import lombok.Getter;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.ReactiveHealthIndicator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class ConfigurableHealthContributor implements ReactiveHealthIndicator {

    @Getter
    private boolean healthy;

    @Override
    public Mono<Health> health() {
        return healthy ? Mono.just(Health.up().build()) : Mono.just(Health.down().build());
    }

    public void setUnhealthy() {
        healthy = false;
    }

    public void setHealthy() {
        healthy = true;
    }
}
