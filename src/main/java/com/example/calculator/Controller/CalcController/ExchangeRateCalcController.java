package com.example.calculator.Controller.CalcController;

import com.example.calculator.DAO.CalculationDTO;
import com.example.calculator.Service.CalcService.ExchangeRateCalcService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExchangeRateCalcController {
    private final ExchangeRateCalcService exchangeRateCalcService;
    public ExchangeRateCalcController(ExchangeRateCalcService exchangeRateCalcService) {this.exchangeRateCalcService=exchangeRateCalcService;}
        @PostMapping("/exchange-rate-calc/calcProc")
        public ResponseEntity<Map<String,String>> getExchangeRate(@RequestBody CalculationDTO data) {
            Double calculationdata = Double.parseDouble(data.getCalculationData());
            String base = data.getBase();
            String target = data.getTarget();
            String res = exchangeRateCalcService.ConvertMoney(base,target,calculationdata);
            Map<String,String> resultmap = new HashMap<>();
            resultmap.put("result",res);
            return new ResponseEntity<>(resultmap, HttpStatus.OK);
        }
    }

