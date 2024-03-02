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

        if (option.equals("bitoint")) {

        }
        return (int)fourBasicCalcService.CalcProc(datastr).doubleValue();
    }
}