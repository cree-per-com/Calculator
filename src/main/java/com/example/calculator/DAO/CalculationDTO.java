package com.example.calculator.DAO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculationDTO {
    private String calculationData;
    private String calculationOption;
    private String type;
    private String value1;
    private String value2;
    private String target;
    private String base;
}
