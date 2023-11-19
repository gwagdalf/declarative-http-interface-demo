package com.example.declarativehttpinterface.demo;

import com.example.declarativehttpinterface.demo.client.DeclarativeHttpInterfaceClient;
import com.example.declarativehttpinterface.demo.client.RestTemplateNWebClient;
import com.example.declarativehttpinterface.demo.model.CurrencyCode;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner init(RestTemplateNWebClient restTemplateClient, DeclarativeHttpInterfaceClient declarativeHttpInterfaceClient){
		return args -> {

			// 1. restTemplate
			double usdRate = restTemplateClient.getRateWithRestTemplate(CurrencyCode.USD);

			// 2. webClient
			double eurRate = restTemplateClient.getRateWithWebclient(CurrencyCode.EUR);

			// 3. declarative http interface
			Map<String, Map<String, Double>> res = declarativeHttpInterfaceClient.getRate();
			double rate = 1 / res.get("rates").get(CurrencyCode.CNY.name());
			log.info(String.format("## %s exchanges rate: %.2f", CurrencyCode.CNY.name(), rate));

		};
	}


}
