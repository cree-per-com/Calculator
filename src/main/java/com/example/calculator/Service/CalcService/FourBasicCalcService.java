
package com.example.calculator.Service.CalcService;

import com.example.calculator.Entity.CalculationData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FourBasicCalcService {
    public Double CalcProc(CalculationData data) {
        String datastr = data.getCalculationData();

        String[] arr = datastr.split("[\\+\\-\\*\\/\\(\\)]");
        ArrayList<Double> num = new ArrayList<>();
        Double result = 0d;
        for(String s : arr) {
            num.add(Double.parseDouble(s));
        }

        String operatorRegex = "[\\+\\-\\*\\/\\(\\)]";
        Pattern operatorPattern = Pattern.compile(operatorRegex);
        Matcher operatorMatcher = operatorPattern.matcher(datastr);
        ArrayList<String> operators = new ArrayList<>();
        while (operatorMatcher.find()) {
            operators.add(operatorMatcher.group());
        }
        for(int i=0; i<arr.length; i++) {
            if (i == 0) {
                result = num.get(i);
            } else {
                String operator = operators.get(i - 1);
                Double operand = num.get(i);
                switch (operator) {
                    case "+":
                        result += operand;
                        break;
                    case "-":
                        result -= operand;
                        break;
                    case "*":
                        result *= operand;
                        break;
                    case "/":
                        if(operand!=0) result /= operand;
                        else result = null;
                        break;
                }
            }
        }

        return result;
    }
}

