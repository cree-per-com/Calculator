package com.example.calculator.Service.CalcService;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Stack;


class FourBasicCalcServiceTest {

    @Test
    void CalcProcTest() {
        DataParsingService dataParsingService = new DataParsingService();
        FourBasicCalcService calcService = new FourBasicCalcService();
        String datastr = "(0.1+0.2)*0.3";
        Stack<BigDecimal> bigDecimalStack = dataParsingService.getnumbersStack(datastr);
        Stack<Character> characterStack = dataParsingService.getoperatorsStack(datastr);
        Double result = calcService.CalcProc(datastr,bigDecimalStack,characterStack);
        Assertions.assertThat(result).isEqualTo(0.09d);
    }
}