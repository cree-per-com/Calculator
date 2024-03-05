package com.example.calculator.Service.CalcService;

import com.example.calculator.DAO.CalculationDataRepository;
import com.example.calculator.Entity.CalculationEntity;
import lombok.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@Service
public class ExchangeRateCalcService {
    private CalculationDataRepository calculationDataRepository;

    public ExchangeRateCalcService(CalculationDataRepository calculationDataRepository) {
        this.calculationDataRepository = calculationDataRepository;
    }

    //base통화에서 target통화로 amonut만큼 환전
    public String ConvertMoney(String base, String target, double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = System.getenv("EXCHANGERATE_APIKEY");
        String url = "http://api.exchangeratesapi.io/v1/latest?access_key="
                + apiKey + "&symbols=" + target + "&base=" + base;

        try {
            Map response = restTemplate.getForObject(url, Map.class);
            Map<String, Double> rates = (Map<String, Double>) response.get("rates"); //base에 대한 전체 환율정보 갖고옴
            Double rate = rates.get(target); //전체 환율정보중에서 target것만 뽑아냄

            double convertedAmount = rate * amount;

            //로그인한 사용자의 계산내역 저장
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
                String username = authentication.getName();// 로그인한 사용자만 처리
                CalculationEntity calculationEntity = new CalculationEntity();
                calculationEntity.setUsername(username);
                calculationEntity.setCalculator("환율 계산기");
                calculationEntity.setCalcstring(base + " to " + target);
                calculationEntity.setResult(String.valueOf(convertedAmount));

                calculationDataRepository.save(calculationEntity); // 계산 기록을 데이터베이스에 저장
            }
                return String.valueOf(convertedAmount);
                // Process the response
            } catch(HttpClientErrorException ex){
                System.out.println("Error body: " + ex.getResponseBodyAsString());
            }
        return null;
        }
    }
