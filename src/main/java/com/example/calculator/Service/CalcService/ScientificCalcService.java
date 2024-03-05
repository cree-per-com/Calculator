package com.example.calculator.Service.CalcService;

import com.example.calculator.DAO.CalculationDataRepository;
import com.example.calculator.Entity.CalculationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ScientificCalcService {
    private FourBasicCalcService fourBasicCalcService;
    private CalculationDataRepository calculationDataRepository;
    @Autowired
    public ScientificCalcService(FourBasicCalcService fourBasicCalcService, CalculationDataRepository calculationDataRepository) {
        this.fourBasicCalcService=fourBasicCalcService;
        this.calculationDataRepository=calculationDataRepository;}

    public String DataParsing(String str) {
        String regex = "[+\\-*/()]";
        String result;
        if(str.matches(".*" + regex + ".*")) result =  fourBasicCalcService.CalcProc(str).toString();
        else result= str;

        //로그인한 사용자의 계산내역 저장
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();// 로그인한 사용자만 처리
            CalculationEntity calculationEntity = new CalculationEntity();
            calculationEntity.setUsername(username);
            calculationEntity.setCalculator("공학용 계산기");
            calculationEntity.setCalcstring(str);
            calculationEntity.setResult(result);

            calculationDataRepository.save(calculationEntity); // 계산 기록을 데이터베이스에 저장
        }
        return result;
    }

    public String CirCalcProc(String whichcir, String circular1) {
        double value = Double.parseDouble(DataParsing(circular1));
        Double res = switch(whichcir) {
            case "sin" -> Math.sin(value);
            case "cos" -> Math.cos(value);
            case "tan" -> {
                if (Math.cos(value) != 0) yield Math.sin(value) / Math.cos(value);
                else throw new ArithmeticException("tan함수가 주어진 값에서 정의되지 않습니다");
            }
            default -> 0d;
        };
        return res.toString();
    }

    public String ExpoCalcProc(String mit, String jisoo) {
        double doublemit = Double.parseDouble(DataParsing(mit));
        double doublejisoo = Double.parseDouble(DataParsing(jisoo));
        return String.valueOf(Math.pow(doublemit,doublejisoo));
    }
    public String LogCalcProc(String mit, String jinsoo) {
        double doublemit = Double.parseDouble(DataParsing(mit));
        double doublejinsoo = Double.parseDouble(DataParsing(jinsoo));
        return String.valueOf(Math.log(doublejinsoo)/Math.log(doublemit));
    }
}
