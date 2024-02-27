package com.example.calculator.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
