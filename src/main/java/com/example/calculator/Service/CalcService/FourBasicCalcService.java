
package com.example.calculator.Service.CalcService;

import com.example.calculator.DTO.CalculationData;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FourBasicCalcService {
    public Double CalcProc(CalculationData data) {
        String op = data.getOperator();
        double one = data.getNum1();
        double two = data.getNum2();

        switch (op) {
            case "+" :
                return one+two;
            case "-" :
                return one-two;
            case "*" :
                return one*two;
            case "/" :
                if(two!=0) return one/two;
                else return null;
            default : return null;
        }
    }
}

