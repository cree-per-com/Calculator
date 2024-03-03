package com.example.calculator.Service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;

@Service
public class BiCalcService {
    private final FourBasicCalcService fourBasicCalcService;

    @Autowired
    public BiCalcService(FourBasicCalcService fourBasicCalcService) {
        this.fourBasicCalcService = fourBasicCalcService;
    }

    public String CalcProc(String datastr, String option) {

        if (option.equals("bitoint")) {
            StringBuilder tempsb = new StringBuilder();
            StringBuilder returnsb = new StringBuilder();

            for (int i = 0; i < datastr.length(); i++) {
                char ch = datastr.charAt(i);
                //ch가 숫자 -> 임시 sb에 추가
                if (Character.isDigit(ch)) {
                    tempsb.append(ch);
                }
                //ch가 연산자 -> 지금까지의 이진수를 십진수로 바꿔서 리턴sb에 추가
                else {
                    // 괄호가 있으면 연산자 두개가 연속해서 나오는거니까 tempsb길이>0인지 체크해야함
                    if (tempsb.length() > 0) {
                        returnsb.append(Integer.parseInt(tempsb.toString(), 2));
                        tempsb.setLength(0); // tempsb 비우기
                    }
                    //연산자 추가
                    returnsb.append(ch);
                }
            }

            // 마지막에 숫자로끝나는경우 십진수로 바꿔서 추가
            if (tempsb.length() > 0) {
                returnsb.append(Integer.parseInt(tempsb.toString(), 2));
            }

            return String.valueOf(fourBasicCalcService.CalcProc(returnsb.toString()));
        }
        else return Integer.toBinaryString((int)fourBasicCalcService.CalcProc(datastr).doubleValue());
    }
}