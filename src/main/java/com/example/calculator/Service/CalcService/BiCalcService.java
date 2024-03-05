package com.example.calculator.Service.CalcService;

import com.example.calculator.DAO.CalculationDataRepository;
import com.example.calculator.Entity.CalculationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Stack;

@Service
public class BiCalcService {
    private final FourBasicCalcService fourBasicCalcService;
    private final CalculationDataRepository calculationDataRepository;

    @Autowired
    public BiCalcService(FourBasicCalcService fourBasicCalcService, CalculationDataRepository calculationDataRepository) {
        this.fourBasicCalcService = fourBasicCalcService;
        this.calculationDataRepository = calculationDataRepository;
    }

    public String CalcProc(String datastr, String option) {
        String result;
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
                    if (!tempsb.isEmpty()) {
                        returnsb.append(Integer.parseInt(tempsb.toString(), 2));
                        tempsb.setLength(0); // tempsb 비우기
                    }
                    //연산자 추가
                    returnsb.append(ch);
                }
            }

            // 마지막에 숫자로끝나는경우 십진수로 바꿔서 추가
            if (!tempsb.isEmpty()) {
                returnsb.append(Integer.parseInt(tempsb.toString(), 2));
            }
            result = String.valueOf(fourBasicCalcService.CalcProc(returnsb.toString()));
        }
        else result =  Integer.toBinaryString((int)fourBasicCalcService.CalcProc(datastr).doubleValue());

        //로그인한 사용자의 계산내역 저장
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();// 로그인한 사용자만 처리
            CalculationEntity calculationEntity = new CalculationEntity();
            calculationEntity.setUsername(username);
            calculationEntity.setCalculator("이진수 계산기");
            calculationEntity.setCalcstring(datastr);
            calculationEntity.setResult(result);

            calculationDataRepository.save(calculationEntity); // 계산 기록을 데이터베이스에 저장
        }

        return result;
    }
}