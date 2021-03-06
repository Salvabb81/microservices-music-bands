package com.github.microservices.musicicians;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.github.microservices.musician.model"})
public class MicroserviceMusiciansApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceMusiciansApplication.class, args);
	}

}
