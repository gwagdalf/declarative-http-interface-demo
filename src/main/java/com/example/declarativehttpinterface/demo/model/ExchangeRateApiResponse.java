package com.example.declarativehttpinterface.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;

@Data
public class ExchangeRateApiResponse implements CurrencyApiResponse {

	private String date;
	private Long time_last_updated;
	private Map<String, Double> rates;
	@JsonProperty("error-type")
	private String error;
	private String result;

	@Override
	public Map<String, Double> getRates() {
		return this.rates;
	}

	@Override
	public String getError() {
		return this.error;
	}

	@Override
	public Boolean isSuccess() {
		return !"error".equals(result);
	}
}
