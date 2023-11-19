package com.example.declarativehttpinterface.demo.model;

import java.math.BigDecimal;
import java.util.Map;

public interface CurrencyApiResponse {
    Map<String, BigDecimal> getRates();
    String getError();
    Boolean isSuccess();
}
