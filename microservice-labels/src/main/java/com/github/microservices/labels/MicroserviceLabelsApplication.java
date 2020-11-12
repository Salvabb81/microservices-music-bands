package com.github.microservices.labels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({ "com.github.microservices.band.model", "com.github.microservices.musician.model",
		"com.github.microservices.country.model", "com.github.microservices.labels.model" })
public class MicroserviceLabelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceLabelsApplication.class, args);
	}

}
