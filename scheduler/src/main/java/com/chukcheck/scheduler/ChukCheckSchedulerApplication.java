package com.chukcheck.scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.chukcheck")
public class ChukCheckSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChukCheckSchedulerApplication.class, args);
	}

}
