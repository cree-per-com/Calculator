/*
package com.example.calculator.Service.CalcService;

import com.example.calculator.DAO.CalculationDataRepository;
import com.example.calculator.Entity.CalculationEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class BiCalcServiceTest {
    @Test
    void calcProcTest() {
        BiCalcService biCalcService = new BiCalcService(new FourBasicCalcService(new CalculationDataRepository);
        String datastr = "1+2";
        String option = "inttobi";
        String result = biCalcService.CalcProc(datastr,option);
        Assertions.assertThat(result).isEqualTo("11");
    }
}

 */