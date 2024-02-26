
package com.example.calculator.Service.CalcService;

import com.example.calculator.DTO.CalculationData;
import org.springframework.stereotype.Service;

@Service
public class FourBasicCalcService {
    private CalculationData data;
    public Long CalcProc(CalculationData data) {
        String op = data.getOperator();
        long one = data.getNum1();
        long two = data.getNum2();
        Long result = 0L;
        switch (op) {
            case "+" :
                result = one+two;
                break;
            case "-" :
                result = one-two;
                break;
            case "*" :
                result = one*two;
                break;
            case "/" :
                if(two!=0) result = one/two;
                else result = null;
                break;
            default : result = null;
        }
        return result;
        /*
        if (op.equals("+")) return one+two;
        else if (op.equals("-")) return one-two;
        else if (op.equals("*")) return one*two;
        else if (op.equals("/")) {
            if (two!=0) return one/two;
            else return null;
        }
        else return null;

         */
    }
}

