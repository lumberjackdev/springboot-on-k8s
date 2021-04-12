package com.lumberjackdev.springbooktk8s;

import lombok.Data;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Data
@Component
public class ConfigurableHealthContributor implements HealthIndicator {

    private Health health = Health.up().build();

    @Override
    public Health health() {
        return health;
    }
}
