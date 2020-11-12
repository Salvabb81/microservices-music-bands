package com.github.microservices.bands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

import org.h2.tools.Server;

@SpringBootApplication
@EntityScan({ "com.github.microservices.band.model", "com.github.microservices.musician.model",
		"com.github.microservices.country.model"})
public class MicroserviceBandsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBandsApplication.class, args);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}

}
