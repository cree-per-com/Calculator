package com.example.calculator.Controller.CalcController;

import com.example.calculator.DAO.CalculationData;
import com.example.calculator.Service.CalcService.DataParsingService;
import com.example.calculator.Service.CalcService.FourBasicCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Stack;


@Controller
public class FourBasicCalcController {
    private final FourBasicCalcService fourBasicCalcService;
    @Autowired
    public FourBasicCalcController(FourBasicCalcService fourBasicCalcService, DataParsingService dataParsingService) {
        this.fourBasicCalcService = fourBasicCalcService;
    }

    @PostMapping("/four-basic-calc/calcProc")
    public ResponseEntity<Double> fourBasicCalcProc(@RequestBody CalculationData data) {
        String datastr = data.getCalculationData();
        Double res = fourBasicCalcService.CalcProc(datastr);
        return ResponseEntity.ok(res);
    }

}
