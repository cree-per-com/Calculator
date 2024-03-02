package com.example.calculator.Controller.CalcController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ExchangeRateCalcController {
        @GetMapping("/exchange-rate-calc")
        public String getExchangeRate() {
            // 환율정보를 가져옴
            String apiUrl = "https://api.exchangeratesapi.io/latest?base=KRW";
            RestTemplate restTemplate = new RestTemplate();
            String exchangeRatesApiResponse = restTemplate.getForObject(apiUrl, String.class);
            // 가져온 환율 정보를 그대로 반환
            return exchangeRatesApiResponse;
        }
    }

