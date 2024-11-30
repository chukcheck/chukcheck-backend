package com.chukcheck.api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.chukcheck.core")
@EntityScan(basePackages = "com.chukcheck.core")
@Configuration
public class ApiConfig {
}
