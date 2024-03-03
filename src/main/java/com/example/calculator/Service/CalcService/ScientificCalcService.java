package com.example.calculator.Service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScientificCalcService {
    private FourBasicCalcService fourBasicCalcService;
    @Autowired
    public ScientificCalcService(FourBasicCalcService fourBasicCalcService) {this.fourBasicCalcService=fourBasicCalcService;}

    public String DataParsing(String str) {
        String regex = "[+\\-*/()]";
        if(str.matches(".*" + regex + ".*")) return fourBasicCalcService.CalcProc(str).toString();
        else return str;
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
