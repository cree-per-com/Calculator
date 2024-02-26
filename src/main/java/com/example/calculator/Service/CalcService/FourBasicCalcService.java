
package com.example.calculator.Service.CalcService;

import com.example.calculator.Entity.CalculationData;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FourBasicCalcService {
    public Double CalcProc(String datastr) {
        // 공백 제거
        datastr = datastr.replace(" ", "");

        // 스택 생성
        Stack<BigDecimal> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int index = 0;
        while (index < datastr.length()) {
            char ch = datastr.charAt(index);
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (index < datastr.length() && (Character.isDigit(datastr.charAt(index)) || datastr.charAt(index) == '.')) {
                    sb.append(datastr.charAt(index));
                    index++;
                }
                numbers.push(new BigDecimal(sb.toString()));
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operators.push(ch);
                index++;
            } else if (ch == '(') {
                operators.push(ch);
                index++;
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    BigDecimal b = numbers.pop();
                    BigDecimal a = numbers.pop();
                    numbers.push(performOperation(a, b, operators.pop()));
                }
                operators.pop(); // '(' 제거
                index++;
            }
        }

        while (!operators.isEmpty()) {
            BigDecimal b = numbers.pop();
            BigDecimal a = numbers.pop();
            numbers.push(performOperation(a, b, operators.pop()));
        }

        return numbers.pop().doubleValue();
    }

    private BigDecimal performOperation(BigDecimal a, BigDecimal b, char operator) {
        switch (operator) {
            case '+':
                return a.add(b);
            case '-':
                return a.subtract(b);
            case '*':
                return a.multiply(b);
            case '/':
                if (b.compareTo(BigDecimal.ZERO) != 0) {
                    return a.divide(b, 10, RoundingMode.HALF_UP);
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}


