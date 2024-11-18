package com.chukcheck.scheduler.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories(basePackages = "com.chukcheck.core")
@EntityScan(basePackages = "com.chukcheck.core")
@EnableScheduling
@Configuration
public class SchedulerConfig {
}

