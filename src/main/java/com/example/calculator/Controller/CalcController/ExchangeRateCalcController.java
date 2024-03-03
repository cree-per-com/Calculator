package com.example.calculator.Controller.CalcController;

import com.example.calculator.DAO.CalculationData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExchangeRateCalcController {
        @PostMapping("/exchange-rate-calc/calcProc")
        public ResponseEntity<Map<String,String>> getExchangeRate(@RequestBody CalculationData data) {
            Map<String,String> resultmap = new HashMap<>();
            resultmap.put("","");
            return new ResponseEntity<>(resultmap, HttpStatus.OK);
        }
    }

