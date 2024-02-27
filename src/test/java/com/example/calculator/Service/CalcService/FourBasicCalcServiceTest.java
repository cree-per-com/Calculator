package com.example.calculator.Service.CalcService;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;



class FourBasicCalcServiceTest {

    @Test
    void CalcProcTest() {
        FourBasicCalcService calcService = new FourBasicCalcService();
        Double result = calcService.CalcProc("0.5+3*0.2");
        Assertions.assertThat(result).isEqualTo(0.7d);
    }
}