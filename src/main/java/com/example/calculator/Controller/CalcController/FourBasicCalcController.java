package com.example.calculator.Controller.CalcController;

import com.example.calculator.Entity.CalculationData;
import com.example.calculator.Service.CalcService.DataParsingService;
import com.example.calculator.Service.CalcService.FourBasicCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.Stack;


@Controller
public class FourBasicCalcController {
    private final FourBasicCalcService fourBasicCalcService;
    private final DataParsingService dataParsingService;
    @Autowired
    public FourBasicCalcController(FourBasicCalcService fourBasicCalcService, DataParsingService dataParsingService) {
        this.fourBasicCalcService = fourBasicCalcService;
        this.dataParsingService = dataParsingService;
    }

    @PostMapping("/four-basic-calc/calcProc")
    public ResponseEntity<Double> fourBasicCalcProc(@RequestBody CalculationData data) {
        System.out.println("Received Data : "+data);
        String datastr = data.getCalculationData();
        Stack<BigDecimal> numbers = dataParsingService.getnumbersStack(datastr);
        Stack<Character> operators = dataParsingService.getoperatorsStack(datastr);
        Double res = fourBasicCalcService.CalcProc(datastr, numbers, operators);
        return ResponseEntity.ok(res);
    }

}
