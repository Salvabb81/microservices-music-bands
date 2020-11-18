package com.github.microservices.bands.services;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.microservices.band.model.Band;
import com.github.microservices.bands.repository.BandRepository;
import com.github.microservices.commons.services.CommonServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BandServiceImpl extends CommonServiceImpl<Band, BandRepository> implements BandService {

	@Value("${elasticsearch.hostname}")
	private String hostname;

	@Value("${elasticsearch.username}")
	private String username;

	@Value("${elasticsearch.password}")
	private String password;

	@Override
	public String getLastTweet() {
		RestHighLevelClient client = createClient();

//		SearchRequest searchRequest = new SearchRequest();
//		searchRequest.

		GetRequest request = new GetRequest();
		request.id("1329001956648640512");

		GetResponse response = null;
		try {
			response = client.get(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			log.error("Error while querying Elasticsearch", e);
		}
		response.toString();

		return null;
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

}
