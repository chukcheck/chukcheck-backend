package com.chukcheck.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.chukcheck")
public class ChukCheckApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChukCheckApiApplication.class, args);
	}

}
