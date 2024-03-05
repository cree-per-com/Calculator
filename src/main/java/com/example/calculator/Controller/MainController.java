package com.example.calculator.Controller;

import com.example.calculator.DAO.CalculationDataRepository;
import com.example.calculator.DAO.DataDTO;
import com.example.calculator.Entity.CalculationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @GetMapping("/")
    public String startPage() {
        return "start";
    }
    @GetMapping("/four-basic-calc")
    public String fourBasicCalc() {
        return "fourbasiccalc";
    }
    @GetMapping("/bi-calc")
    public String biCalc() {return "bicalc";}

    @GetMapping("/scientific-calc")
    public String SciCalc() {
        return "scientificcalc";
    }
    @GetMapping("/exchange-rate-calc")
    public String ExRateCalc() {
        return "exchangeratecalc";
    }

    @Autowired
    private CalculationDataRepository calculationDataRepository;
    @GetMapping("/mypage")
    public String MyCalcData(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<DataDTO> mydata = calculationDataRepository.findCalculationEntitiesByUsername(username);
        model.addAttribute("mydatalist",mydata);
        model.addAttribute("username",username);
        return "mypage";
    }
}
