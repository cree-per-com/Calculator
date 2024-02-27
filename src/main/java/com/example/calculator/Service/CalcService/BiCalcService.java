package com.example.calculator.Service.CalcService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiCalcService {
    private FourBasicCalcService fourBasicCalcService;
    @Autowired
    public BiCalcService(FourBasicCalcService fourBasicCalcService) {
        this.fourBasicCalcService = fourBasicCalcService;
    }
}
