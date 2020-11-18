package com.github.kafka.conumer.twitter;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.google.gson.JsonParser;

@Component
public class TwitterConsumer implements CommandLineRunner {

	@Value("${elasticsearch.hostname}")
	private String hostname;
	
	@Value("${elasticsearch.username}")
	private String username;
	
	@Value("${elasticsearch.password}")
	private String password;

	public void run(String... args) throws IOException {
		Logger logger = LoggerFactory.getLogger(TwitterConsumer.class.getName());

		RestHighLevelClient client = createClient();
		KafkaConsumer<String, String> consumer = createConsumer("twitter_tweets");

		// poll for new data
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

			Integer recordCount = records.count();

			logger.info("Received " + recordCount + " records.");

			BulkRequest bulkRequest = new BulkRequest();

			for (ConsumerRecord<String, String> record : records) {

				// twitter feed specific id
				try {
					String id = extractIdFromTweet(record.value());

					IndexRequest indexRequest = new IndexRequest("twitter").source(record.value(), XContentType.JSON)
							.id(id);

					bulkRequest.add(indexRequest); // we add to our bulk request (takes no time)
				} catch (NullPointerException e) {
					logger.warn("skipping bad data: " + record.value());
				}

			}

			if (recordCount > 0) {
				BulkResponse bulkItemResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
				logger.info("Committing offsets...");
				consumer.commitSync();
				logger.info("Offsets have been committed");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static KafkaConsumer<String, String> createConsumer(String topic) {
		String bootstrapServers = "127.0.0.1:9092";
		String groupId = "kafka-demo-elasticsearch";

		// create consumer config
		Properties properties = new Properties();
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");

		// create consumer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList(topic));

		return consumer;
	}

	private RestHighLevelClient createClient() {

		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

		RestClientBuilder builder = RestClient.builder(new HttpHost(hostname, 443, "https"))
				.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					}
				});

		RestHighLevelClient client = new RestHighLevelClient(builder);
		return client;
	}

	private static String extractIdFromTweet(String tweetJson) {
		// gson library
		return JsonParser.parseString(tweetJson).getAsJsonObject().get("id_str").getAsString();
	}

}
