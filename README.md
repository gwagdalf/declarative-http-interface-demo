# declarative-http-interface-demo


## 설명
https://docs.spring.io/spring-framework/reference/integration/rest-clients.html 를 참고하여, 

총 7개의 http client 사용 demo 로 구성 되어 있습니다.

첫 3개는 Rest Template, WebClient, RestClient 를 사용한 demo 를 구현하였습니다.

나머지 4개는 declarative http interface 사용하여 구현하였습니다.
* RestTemplate + declarative http interface
* WebClient + declarative http interface
* RestClient + declarative http interface
* OpenFeignClient + declarative http interface  


## 최소 version
아래는 최소 사용 version 정보 입니다 ( from BARD AI)

| type          | Spring Boot Version | Spring Version |
|---------------|---------------------|----------------|
| Rest Template | 1.0                 | 3.0            |
| WebClient     | 2.0                 | 5.0            |
| RestClient    | 3.2                 | 6.1            |


## Example API
Example API 로는 환율정보 조회 사이트를 사용했습니다.
* https://open.er-api.com/v6/latest/USD
* https://open.er-api.com/v6/latest/KRW


## 주요 코드
https://github.com/gwagdalf/declarative-http-interface-demo/blob/main/src/main/java/com/example/declarativehttpinterface/demo/DemoApplication.java#L33-L61

```
// 1. restTemplate
double usdRate = myHttpClient.getRateWithRestTemplate(CurrencyCode.USD);

// 2. webClient
double eurRate = myHttpClient.getRateWithWebclient(CurrencyCode.EUR);

// 3. restClient
double jpyRate = myHttpClient.getRateWithRestclient(CurrencyCode.JPY);

// declarative http interface
log.info("=== declarative http interface ===");

// 11. restTemplate
Map<String, Map<String, Double>> res1 = restTemplateDeclarativeInterface.getRate();
CurrencyCode currencyCode = CurrencyCode.USD;
double rate1 = 1 / res1.get("rates").get(currencyCode.name());
log.info(String.format("## %s exchanges rate: %.2f", currencyCode.name(), rate1));

// 12. webClient
currencyCode = CurrencyCode.EUR;
Map<String, Map<String, Double>> res2 = webClientDeclarativeInterface.getRate();
double rate2 = 1 / res2.get("rates").get(currencyCode.name());
log.info(String.format("## %s exchanges rate: %.2f", currencyCode.name(), rate2));

// 13. restClient
currencyCode = CurrencyCode.JPY;
Map<String, Map<String, Double>> res3 = restClientDeclarativeInterface.getRate();
double rate3 = 1 / res2.get("rates").get(currencyCode.name());
log.info(String.format("## %s exchanges rate: %.2f", currencyCode.name(), rate3));

// 14. openFeign
currencyCode = CurrencyCode.CNY;
Map<String, Map<String, Double>> res4 = openFeignClientInterface.getRate();
double rate4 = 1 / res2.get("rates").get(currencyCode.name());
log.info(String.format("[OpenFeign+declarative] %s exchanges rate: %.2f", currencyCode.name(), rate3));
```

### Result
![image](https://github.com/gwagdalf/declarative-http-interface-demo/assets/34761230/b30194df-a6a9-429b-b139-178ff318efaf)


