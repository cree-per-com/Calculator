package com.example.calculator.Service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;

@Service
public class BiCalcService{
    private final DataParsingService dataParsingService;

    @Autowired
    public BiCalcService(DataParsingService dataParsingService) {
        this.dataParsingService=dataParsingService;
    }

    public Integer CalcProc(String datastr, String option) {
        Stack<BigDecimal> numbers = dataParsingService.getnumbersStack(datastr);
        Stack<Character> operators = dataParsingService.getoperatorsStack(datastr);
        int index=0;
        if(option=="bitoint") {
            for (BigDecimal num : numbers) {
                num = new BigDecimal(new BigInteger(String.valueOf(num), 2));
            }
        }
        while(index<datastr.length()) {
            char ch = datastr.charAt(index);
            if(ch == ')') {
                while (operators.peek() != '(') {
                    BigDecimal b = numbers.pop();
                    BigDecimal a = numbers.pop();
                    numbers.push(performOperation(a, b, operators.pop()));
                }
                operators.pop(); // '(' 제거
                index++;
            }
            else index++;
        }
        //계산 로직
        while (!operators.isEmpty()) {
            BigDecimal b = numbers.pop();
            BigDecimal a = numbers.pop();
            numbers.push(performOperation(a, b, operators.pop()));
        }

        return numbers.pop().intValue();
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
