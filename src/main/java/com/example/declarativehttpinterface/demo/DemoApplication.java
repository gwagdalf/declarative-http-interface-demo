package com.example.declarativehttpinterface.demo;

import com.example.declarativehttpinterface.demo.client.MyHttpClient;
import com.example.declarativehttpinterface.demo.client.OpenFeignClientInterface;
import com.example.declarativehttpinterface.demo.client.RestClientDeclarativeInterface;
import com.example.declarativehttpinterface.demo.client.RestTemplateDeclarativeInterface;
import com.example.declarativehttpinterface.demo.client.WebClientDeclarativeInterface;
import com.example.declarativehttpinterface.demo.model.CurrencyCode;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  ApplicationRunner init(MyHttpClient myHttpClient
      , WebClientDeclarativeInterface webClientDeclarativeInterface
      , RestClientDeclarativeInterface restClientDeclarativeInterface
      , RestTemplateDeclarativeInterface restTemplateDeclarativeInterface
			, OpenFeignClientInterface openFeignClientInterface
  ) {
    return args -> {

      // 1. restTemplate
      double usdRate = myHttpClient.getRateWithRestTemplate(CurrencyCode.USD);

      // 2. webClient
      double eurRate = myHttpClient.getRateWithWebclient(CurrencyCode.EUR);

      // 3. restClient
      double jpyRate = myHttpClient.getRateWithRestclient(CurrencyCode.JPY);

      // declarative http interface
      log.info("=== declarative http interface ===");

      // 11. restTemplate
      CurrencyCode currencyCode = CurrencyCode.USD;
      Map<String, Map<String, Double>> res1 = restTemplateDeclarativeInterface.getRate();
      double rate1 = 1 / res1.get("rates").get(currencyCode.name());
      log.info(String.format("[RestTemplate+declarative] %s exchanges rate: %.2f", currencyCode.name(), rate1));

      // 12. webClient
      currencyCode = CurrencyCode.EUR;
      Map<String, Map<String, Double>> res2 = webClientDeclarativeInterface.getRate();
      double rate2 = 1 / res2.get("rates").get(currencyCode.name());
      log.info(String.format("[WebClient+declarative] %s exchanges rate: %.2f", currencyCode.name(), rate2));

      // 13. restClient
      currencyCode = CurrencyCode.JPY;
      Map<String, Map<String, Double>> res3 = restClientDeclarativeInterface.getRate();
      double rate3 = 1 / res3.get("rates").get(currencyCode.name());
      log.info(String.format("[RestClient+declarative] %s exchanges rate: %.2f", currencyCode.name(), rate3));

			// 14. openFeign
			currencyCode = CurrencyCode.CNY;
			Map<String, Map<String, Double>> res4 = openFeignClientInterface.getRate();
			double rate4 = 1 / res4.get("rates").get(currencyCode.name());
			log.info(String.format("[OpenFeign+declarative] %s exchanges rate: %.2f", currencyCode.name(), rate4));

    };
  }


}
