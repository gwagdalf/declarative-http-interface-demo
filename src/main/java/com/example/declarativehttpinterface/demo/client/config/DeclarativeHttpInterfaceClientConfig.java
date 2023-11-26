package com.example.declarativehttpinterface.demo.client.config;

import com.example.declarativehttpinterface.demo.client.RestClientDeclarativeInterface;
import com.example.declarativehttpinterface.demo.client.RestTemplateDeclarativeInterface;
import com.example.declarativehttpinterface.demo.client.WebClientDeclarativeInterface;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.client.support.RestTemplateAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;

// https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-interface
@Configuration
public class DeclarativeHttpInterfaceClientConfig {

	@Value("${api.exchangeRate.url}")
	private String exchangeRateApiUrl;

	@Bean
	public WebClientDeclarativeInterface webClientDeclarativeInterface() {
		WebClient webClient = WebClient.builder().baseUrl(this.exchangeRateApiUrl).build();
		WebClientAdapter adapter = WebClientAdapter.create(webClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

		return factory.createClient(WebClientDeclarativeInterface.class);
	}

	@Bean
	public RestClientDeclarativeInterface restClientDeclarativeInterface() {
		RestClient restClient = RestClient.builder().baseUrl(this.exchangeRateApiUrl).build();
		RestClientAdapter adapter = RestClientAdapter.create(restClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

		return factory.createClient(RestClientDeclarativeInterface.class);
	}

	@Bean
	public RestTemplateDeclarativeInterface restTemplateDeclarativeInterface() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(this.exchangeRateApiUrl));
		RestTemplateAdapter adapter = RestTemplateAdapter.create(restTemplate);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

		return factory.createClient(RestTemplateDeclarativeInterface.class);
	}
}
