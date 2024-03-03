
package com.example.calculator.Controller.CalcController;

import com.example.calculator.DAO.CalculationData;
import com.example.calculator.Service.CalcService.BiCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class BiCalcController {
    private final BiCalcService biCalcService;
    @Autowired
    public BiCalcController(BiCalcService biCalcService) {
        this.biCalcService =biCalcService;
    }
   @PostMapping("/bi-calc/calcProc")
    public ResponseEntity<String> programmingCalcProc(@RequestBody CalculationData data) {
        String datastr = data.getCalculationData();
        String option = data.getCalculationOption();
        String res = biCalcService.CalcProc(datastr, option);
       return ResponseEntity.ok(res);
   }

}


