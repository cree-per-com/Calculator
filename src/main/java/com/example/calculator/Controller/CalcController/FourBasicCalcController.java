package com.example.calculator.Controller.CalcController;

import com.example.calculator.DTO.CalculationData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class FourBasicCalcController {
    @PostMapping("/four-basic-calc/calcProc")
    public ResponseEntity<CalculationData> fourBasicCalcProc(@RequestBody CalculationData data) {
        System.out.println("Received Data : "+data);
        return ResponseEntity.ok(data);
    }

}
