package com.example.calculator.Service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;

@Service
public class BiCalcService {
    private final DataParsingService dataParsingService;
    private final FourBasicCalcService fourBasicCalcService;

    @Autowired
    public BiCalcService(DataParsingService dataParsingService, FourBasicCalcService fourBasicCalcService) {
        this.dataParsingService = dataParsingService;
        this.fourBasicCalcService = fourBasicCalcService;
    }

    public Integer CalcProc(String datastr, String option) {
        Stack<BigDecimal> numbers = dataParsingService.getnumbersStack(datastr);
        Stack<Character> operators = dataParsingService.getoperatorsStack(datastr);

        if (option.equals("bitoint")) {
            Stack<BigDecimal> tempStack = new Stack<>();
            while (!numbers.isEmpty()) {
                BigDecimal num = numbers.pop(); // 스택에서 요소 추출
                String binaryString = num.toString(); // 이진수 형식의 문자열로 변환
                BigInteger binaryValue = new BigInteger(binaryString, 2); // 이진수 문자열을 BigInteger로 파싱
                tempStack.push(new BigDecimal(binaryValue)); // 파싱한 값으로 새로운 BigDecimal 객체 생성하여 임시 스택에 넣음
            }

            // 임시 스택의 요소를 다시 원본 스택에 넣음
            while (!tempStack.isEmpty()) {
                numbers.push(tempStack.pop());
            }
        }
        return (int)fourBasicCalcService.CalcProc(datastr, numbers, operators).doubleValue();
    }
}