package com.github.kafka.conumer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.kafka.conumer.twitter.TwitterConsumer;

@SpringBootApplication
public class MicroserviceKafkaConsumerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MicroserviceKafkaConsumerApplication.class, args);
		new TwitterConsumer().runConsumer();
	}

}
