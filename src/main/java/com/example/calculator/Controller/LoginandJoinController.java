package com.example.calculator.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginandJoinController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }
}
