package com.example.calculator.Controller.CalcController;

import com.example.calculator.DAO.CalculationDTO;
import com.example.calculator.Service.CalcService.FourBasicCalcService;
import com.example.calculator.Service.CalcService.ScientificCalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ScientificCalcController {
    private final ScientificCalcService scientificCalcService;
    private final FourBasicCalcService fourBasicCalcService;

    @Autowired
    public ScientificCalcController (ScientificCalcService scientificCalcService,
                                     FourBasicCalcService fourBasicCalcService)
    {this.scientificCalcService=scientificCalcService;
     this.fourBasicCalcService=fourBasicCalcService;}


    @PostMapping("/scientific-calc/calcProc")
    public ResponseEntity<Map<String,String>> SciCalcProc(@RequestBody CalculationDTO data) {
        String type = data.getType();
        String value1="";
        String value2="";
        if(data.getValue1()!=null && data.getValue2()!=null) {
            value1 = data.getValue1().replaceAll("e", "2.7")
                    .replaceAll("pi", "3.14");
            value2 = data.getValue2().replaceAll("e", "2.7")
                    .replaceAll("pi", "3.14");
        }
        String datastr = data.getCalculationData();

        String res = switch (type) {
            case "calcCir" -> scientificCalcService.CirCalcProc(value1, value2);
            case "calcExpo" -> scientificCalcService.ExpoCalcProc(value1, value2);
            case "calcLog" -> scientificCalcService.LogCalcProc(value1, value2);
            case "calcBasic" ->
                fourBasicCalcService.CalcProc(datastr).toString();

            default -> "";
        };
        Map<String,String> map = new HashMap<>();
        map.put("result",res);
        return ResponseEntity.ok(map);
    }
}
