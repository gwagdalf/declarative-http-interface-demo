package com.example.declarativehttpinterface.demo.config;

import com.example.declarativehttpinterface.demo.client.DeclarativeHttpInterfaceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class DeclarativeHttpInterfaceClientConfig {

	@Value("${api.exchangeRate.url}")
	private String exchangeRateApiUrl;

	@Bean
	public DeclarativeHttpInterfaceClient declarativeHttpInterfaceClient(WebClient.Builder builder) {
		var client = builder.baseUrl(this.exchangeRateApiUrl).build();
		var factory = HttpServiceProxyFactory
				.builder(WebClientAdapter.forClient(client))
				.build();
		return factory.createClient(DeclarativeHttpInterfaceClient.class);
	}
}
