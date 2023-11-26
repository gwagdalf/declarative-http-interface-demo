//package com.example.declarativehttpinterface.demo.client;
//
//import java.util.List;
//import java.util.Map;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.service.annotation.GetExchange;
//
//@FeignClient(value = "openFeignClientInterface", url = "https://open.er-api.com")
//public interface OpenFeignClientInterface {
//  @RequestMapping(method = RequestMethod.GET, value = "/v6/latest/KRW")
//  Map getRate();
//
//}
