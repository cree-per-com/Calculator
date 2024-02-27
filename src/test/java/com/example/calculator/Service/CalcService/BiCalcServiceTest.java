package com.example.calculator.Service.CalcService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class BiCalcServiceTest {
    @Test
    void calcProcTest() {
        BiCalcService biCalcService = new BiCalcService(new DataParsingService(), new FourBasicCalcService());
        String datastr = "1010+10";
        String option = "bitoint";
        Integer result = biCalcService.CalcProc(datastr,option);
        Assertions.assertThat(result).isEqualTo(12);
    }
}