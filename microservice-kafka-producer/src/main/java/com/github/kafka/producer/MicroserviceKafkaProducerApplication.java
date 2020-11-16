package com.github.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.kafka.producer.twitter.TwitterProducer;

@SpringBootApplication
public class MicroserviceKafkaProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceKafkaProducerApplication.class, args);
		new TwitterProducer().run();
	}

}
