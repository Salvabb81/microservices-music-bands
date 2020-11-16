package com.github.kafka.producer.twitter;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

@Component
public class TwitterProducer {
	private Logger logger = LoggerFactory.getLogger(TwitterProducer.class.getName());

	private String consumerKey = "je6DeAd8D5hQytR3MkXJGbH2x";
	private String consumerSecret = "c2pByb4HeeNbsRCbY1AosAE0PAsHRfVKsMuiSIq4V4B3WVM8VH";
	private String token = "1322933883969613824-mtBQH1e7nt5U1gi2Ucsd2P0GeiTBIL";
	private String secret = "RLvtHr5KQJBKkOfyqZ2Wbtxc3kpysP1CRfo76bmbyQaQy";

	List<String> terms = Lists.newArrayList("musicians");

	public TwitterProducer() {

	}

	public void run() {

		logger.info("Setup");

		BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>(1000);

		Client client = createTwitterClient(msgQueue);

		client.connect();

		KafkaProducer<String, String> producer = createKafkaProducer();

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			logger.info("Stopping aplication...");
			logger.info("Shutting down client from Twitter...");
			client.stop();
			logger.info("Closing producer...");
			producer.close();
			logger.info("done!");
		}));

		// loop to send tweets to kafka topic
		while (!client.isDone()) {
			String msg = null;
			try {
				msg = msgQueue.poll(5, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
				client.stop();
			}
			if (msg != null) {
				logger.info(msg);
				producer.send(new ProducerRecord<String, String>("twitter_tweets", null, msg), new Callback() {

					@Override
					public void onCompletion(RecordMetadata metadata, Exception e) {
						if (e != null) {
							logger.error("Something bad happened", e);
						}

					}
				});
			}
		}
		logger.info("End up application");
	}

	public Client createTwitterClient(BlockingQueue<String> msgQueue) {

		Hosts hosts = new HttpHosts(Constants.STREAM_HOST);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();

		endpoint.trackTerms(terms);

		Authentication auth = new OAuth1(consumerKey, consumerSecret, token, secret);

		ClientBuilder builder = new ClientBuilder().name("client-01") // optional: mainly for the logs
				.hosts(hosts).authentication(auth).endpoint(endpoint).processor(new StringDelimitedProcessor(msgQueue));

		Client client = builder.build();
		return client;
	}

	public KafkaProducer<String, String> createKafkaProducer() {
		String bootstrapServers = "127.0.0.1:9092";

		// Create Producer Properties
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// create safe Producer
		properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
		properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");

		// high throughput producer (at the expense of a bit of latency and CPU usage)
		properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
		properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
		properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32 * 1024)); // 32 KB batch size

		// create the producer
		return new KafkaProducer<String, String>(properties);
	}
}
