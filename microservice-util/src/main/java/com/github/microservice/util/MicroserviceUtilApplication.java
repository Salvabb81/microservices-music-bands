package com.github.microservice.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@EnableCaching
@SpringBootApplication
@EntityScan({"com.github.microservices.country.model"})
public class MicroserviceUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUtilApplication.class, args);
	}

}
