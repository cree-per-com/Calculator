/*
package com.example.calculator.Controller.CalcController;

import com.example.calculator.Entity.CalculationData;
import com.example.calculator.Service.CalcService.BiCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BiCalcController {
    private BiCalcService biCalcService;
    @Autowired
    public BiCalcController(BiCalcService biCalcService) {
        this.biCalcService =biCalcService;
    }
   @PostMapping("/programming-calc/calcProc")
    public ResponseEntity<String> programmingCalcProc(@RequestBody CalculationData data) {
        String datastr = data.getCalculationData();
        Integer res = biCalcService.CalcProc(datastr);
        String resString = "계산 결과는 이진법으로 "+String.valueOf(res) +
                            ", 십진법으로 "+String.valueOf(Integer.toBinaryString(res))
                            +" 입니다.";
       return ResponseEntity.ok(resString);
   }

}

 */
