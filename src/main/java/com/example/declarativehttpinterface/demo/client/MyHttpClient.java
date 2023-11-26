package com.example.declarativehttpinterface.demo.client;

import com.example.declarativehttpinterface.demo.model.CurrencyCode;
import java.util.Map;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Data
@Configuration
public class MyHttpClient {

  @Value("${api.exchangeRate.url}")
  private String exchangeRateApiUrl;

  public double getRateWithRestTemplate(CurrencyCode currency) {
    RestTemplate rt = new RestTemplate();
    Map<String, Map<String, Double>> res = rt.getForObject(this.exchangeRateApiUrl + "/v6/latest/KRW", Map.class);

    double rate = 1 / res.get("rates").get(currency.name());
    log.info(String.format("## %s exchanges rate: %.2f", currency.name(), rate));
    return rate;
  }

  public double getRateWithWebclient(CurrencyCode currency) {
    WebClient client = WebClient.create(exchangeRateApiUrl);
    Map<String, Map<String, Double>> res = client.get()
        .uri("/v6/latest/KRW")
        .retrieve()
        .bodyToMono(Map.class)
        .block();

    double rate = 1 / res.get("rates").get(currency.name());
    log.info(String.format("## %s exchanges rate: %.2f", currency.name(), rate));
    return rate;
  }

  public double getRateWithRestclient(CurrencyCode currency) {
    RestClient client = RestClient.create(exchangeRateApiUrl);
    Map<String, Map<String, Double>> res = client
        .get()
        .uri("/v6/latest/KRW")
        .retrieve()
        .body(Map.class);

    double rate = 1 / res.get("rates").get(currency.name());
    log.info(String.format("## %s exchanges rate: %.2f", currency.name(), rate));
    return rate;
  }
}
