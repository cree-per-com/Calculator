
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
        //공백 문자가 포함되었으면 제거
        datastr = datastr.replace(" ", "");

        Stack<BigDecimal> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int index = 0;
        //datastr을 하나씩 읽는다
        while (index < datastr.length()) {
            char ch = datastr.charAt(index);
            //숫자나 소수점'.'인 경우 : BigDecimal형으로 바꿔서 numbers 스택에 넣음
            if (Character.isDigit(ch) || ch == '.') {
                StringBuffer sb = new StringBuffer();
                while (index < datastr.length() && (Character.isDigit(datastr.charAt(index)) || datastr.charAt(index) == '.')) {
                    sb.append(datastr.charAt(index));
                    index++;
                }
                numbers.push(new BigDecimal(sb.toString()));

                //연산자인 경우 : operators 스택에 넣음
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                operators.push(ch);
                index++;

                //( 괄호인 경우 : operators 스택에 넣음
            } else if (ch == '(') {
                operators.push(ch);
                index++;
                //)괄호인 경우 : ()(빈괄호)가 아닐시 numbers스택에서 숫자를 꺼내서 operators스택에서 꺼낸 연산자로 계산함.
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
                    return a.divide(b, 6, RoundingMode.HALF_UP);
                } else {
                    throw new ArithmeticException("Division by zero");
                }
            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}


