package com.example.calculator.Service.CalcService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Stack;

@Service
public class DataParsingService {
    public Stack<BigDecimal> getnumbersStack(String datastr) {
        //공백 제거
        datastr = datastr.replace(" ", "");
        Stack<BigDecimal> numbers = new Stack<>();
        int index = 0;
        //datastr을 하나씩 읽는다
        while (index < datastr.length()) {
            char ch = datastr.charAt(index);
            //숫자나 소수점'.'인 경우 : BigDecimal형으로 바꿔서 numbers 스택에 넣음
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (index < datastr.length() &&
                        (Character.isDigit(datastr.charAt(index)) || datastr.charAt(index) == '.')) {
                    sb.append(datastr.charAt(index));
                    index++;
                }
                numbers.push(new BigDecimal(sb.toString()));
            }
            else index++;
        }
        return numbers;
    }

    public Stack<Character> getoperatorsStack(String datastr) {
        // 공백 제거
        datastr = datastr.replace(" ", "");
        Stack<Character> operators = new Stack<>();
        for (char ch : datastr.toCharArray()) {
            // 연산자인 경우 : operators 스택에 넣음
            if ("+-*/()".indexOf(ch) != -1) {
                operators.push(ch);
            }
        }
        return operators;
    }
    }
