package com.example.calculator.Service.CalcService;

import com.example.calculator.DAO.CalculationDataRepository;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@Service
public class ExchangeRateCalcService {
    private CalculationDataRepository calculationDataRepository;

    public ExchangeRateCalcService(CalculationDataRepository calculationDataRepository) {
        this.calculationDataRepository=calculationDataRepository;}
    private String apiKey = System.getenv("EXCHANGERATE_APIKEY");

    //base통화에서 target통화로 amonut만큼 환전
    public String ConvertMoney(String base, String target, double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.exchangeratesapi.io/v1/latest?access_key="
                + apiKey + "&symbols=" + target + "&base=" + base;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        Map<String, Double> rates = (Map<String, Double>) response.get("rates"); //base에 대한 전체 환율정보 갖고옴
        Double rate = rates.get(target); //전체 환율정보중에서 target것만 뽑아냄

        double convertedAmount = rate * amount;
        return String.valueOf(convertedAmount);
    }
}
