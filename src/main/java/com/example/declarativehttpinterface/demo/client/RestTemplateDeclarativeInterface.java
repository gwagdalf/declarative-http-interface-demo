package com.example.declarativehttpinterface.demo.client;

import java.util.Map;
import org.springframework.web.service.annotation.GetExchange;

public interface RestTemplateDeclarativeInterface {
  @GetExchange("/v6/latest/KRW")
  Map getRate();
}
