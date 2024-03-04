package com.example.calculator.Service.CalcService;

import com.example.calculator.DAO.CalculationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Collections;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class FourBasicCalcService {

    private CalculationDataRepository calculationDataRepository;
    public FourBasicCalcService(CalculationDataRepository calculationDataRepository) {this.calculationDataRepository=calculationDataRepository;}
    private static int precedence(char op) {
        int res = 0;
        if (op == '+' || op == '-') res= 1;
        else if (op == '*' || op == '/') res= 2;
        else res = -1;
        return res;
    }

    public Double CalcProc(String str) {
        Stack<BigDecimal> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        char[] charr = str.toCharArray();

        for (int i = 0; i < charr.length; i++) {
            if (charr[i] == ' ') continue; //공백 문자는 스킵

            //숫자가 나오면 스트링빌더에 담아뒀다가 연산자 나오면 숫자나오는게 끝난거니까 한번에 values에 푸시
            if (Character.isDigit(charr[i]) || charr[i] == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < charr.length && (Character.isDigit(charr[i]) || charr[i] == '.')) {
                    sb.append(charr[i++]);
                }
                values.push(new BigDecimal(sb.toString()));
                i--;
                // ( 괄호가 나오면 일단 그냥 연산자스택에 푸시
            } else if (charr[i] == '(') {
                operators.push(charr[i]);
                // ) 괄호가 나오면 연산자스택에서 ( 괄호가 픽될때까지 계산해서 밸류스택에 푸시
            } else if (charr[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(performOperation(values.pop(), values.pop(), operators.pop()));
                }
                operators.pop(); // ) 괄호는 아예 연산자스택에 안넣었으므로 (만 팝해도 됨
                //연산자가 나오면 우선순위를 비교한다.
            } else if ("+-*/".indexOf(charr[i]) >= 0) {
                //스택 안의 연산자의 우선순위>=charr[i]우선순위면 스택안의 연산자와 밸류스택에 있는 연산자 두개를 팝해와서 계산
                while (!operators.empty() && precedence(operators.peek()) >= precedence(charr[i])) {
                    values.push(performOperation(values.pop(), values.pop(), operators.pop()));
                }
                operators.push(charr[i]);
            }
        }
        //datastr의 모든 글자를 다읽으면 연산자스택이 빌때까지 계산을 계속함
        while (!operators.empty()) {
            values.push(performOperation(values.pop(), values.pop(), operators.pop()));
        }

        return values.pop().doubleValue();
    }

    private BigDecimal performOperation(BigDecimal b, BigDecimal a, char operator) {
        switch (operator) {
            case '+': return a.add(b);
            case '-': return a.subtract(b);
            case '*': return a.multiply(b);
            case '/':
                if (b.compareTo(BigDecimal.ZERO) == 0) {
                    throw new ArithmeticException("0으로 나눌 수 없습니다.");
                }
                return a.divide(b, 6, RoundingMode.HALF_UP);
            default:
                throw new IllegalArgumentException("이 연산자는 지원되지 않습니다: " + operator);
        }
    }
}

