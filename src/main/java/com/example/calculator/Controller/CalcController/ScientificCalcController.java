package com.example.calculator.Controller.CalcController;

import com.example.calculator.DAO.CalculationData;
import com.example.calculator.Service.CalcService.FourBasicCalcService;
import com.example.calculator.Service.CalcService.ScientificCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScientificCalcController {
    private final ScientificCalcService scientificCalcService;
    private final FourBasicCalcService fourBasicCalcService;

    @Autowired
    public ScientificCalcController (ScientificCalcService scientificCalcService,
                                     FourBasicCalcService fourBasicCalcService)
    {this.scientificCalcService=scientificCalcService;
     this.fourBasicCalcService=fourBasicCalcService;}

    @PostMapping("/scientific-calc/calcProc")
    public ResponseEntity<String> SciCalcProc(@RequestBody CalculationData data) {
        String type = data.getType();
        String value1 = data.getValue1().replaceAll("e","2.7")
                                        .replaceAll("pi","3.14");
        String value2 = data.getValue2().replaceAll("e","2.7")
                                        .replaceAll("pi","3.14");

        String res = switch (type) {
            case "calcCir" -> scientificCalcService.CirCalcProc(value1, value2);
            case "calcExpo" -> scientificCalcService.ExpoCalcProc(value1, value2);
            case "calcLog" -> scientificCalcService.LogCalcProc(value1, value2);
            case "calcBasic" -> {
                String datastr = data.getCalculationData();
                yield fourBasicCalcService.CalcProc(datastr).toString();
            }
            default -> "";
        };
        return ResponseEntity.ok(res);
    }
}
