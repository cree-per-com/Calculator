package com.example.calculator.Service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Stack;

@Service
public class FourBasicCalcService {
    public Double CalcProc(String datastr, Stack<BigDecimal> bigDecimalStack, Stack<Character> characterStack) {
         Stack<BigDecimal> numbers = bigDecimalStack;
         Stack<Character> operators = characterStack;
        //계산 로직
        while (!operators.isEmpty()) {
            if(operators.peek()==')') {
                operators.pop();
                while (operators.peek() != '(') {
                    BigDecimal b = numbers.pop();
                    BigDecimal a = numbers.pop();
                    numbers.push(performOperation(a, b, operators.pop()));
                }
                operators.pop();
            }
            else {
                BigDecimal b = numbers.pop();
                BigDecimal a = numbers.pop();
                numbers.push(performOperation(a, b, operators.pop()));
            }
        }

        return numbers.pop().doubleValue();
    }

    //연산 수행
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
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
            default:
                throw new IllegalArgumentException("입력 방식이 올바르지 않아 계산 과정 중 오류가 발생했습니다.");
        }
    }
}


