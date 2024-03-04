package com.example.calculator.Controller.CalcController;

import com.example.calculator.DAO.CalculationDTO;
import com.example.calculator.Service.CalcService.FourBasicCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class FourBasicCalcController {
    private final FourBasicCalcService fourBasicCalcService;
    @Autowired
    public FourBasicCalcController(FourBasicCalcService fourBasicCalcService) {
        this.fourBasicCalcService = fourBasicCalcService;
    }

    @PostMapping("/four-basic-calc/calcProc")
    public ResponseEntity<String> fourBasicCalcProc(@RequestBody CalculationDTO data) {
        String datastr = data.getCalculationData();
        Double res = fourBasicCalcService.CalcProc(datastr);
        return ResponseEntity.ok(res.toString());
    }

}
