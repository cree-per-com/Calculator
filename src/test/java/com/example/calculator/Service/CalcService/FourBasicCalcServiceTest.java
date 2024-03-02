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
        String datastr = "2.5*(4*(8-6)+2)+10*(7/2)";

        Double result = calcService.CalcProc(datastr);

        Assertions.assertThat(result).isEqualTo(60d);
    }
}