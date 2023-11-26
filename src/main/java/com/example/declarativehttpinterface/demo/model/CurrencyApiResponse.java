package com.example.declarativehttpinterface.demo.model;

import java.util.Map;

public interface CurrencyApiResponse {
    Map<String, Double> getRates();
    String getError();
    Boolean isSuccess();
}
