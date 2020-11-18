package com.github.kafka.conumer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroserviceKafkaConsumerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MicroserviceKafkaConsumerApplication.class, args);
	}

}
