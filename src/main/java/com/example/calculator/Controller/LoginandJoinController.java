package com.example.calculator.Controller;

import com.example.calculator.DAO.JoinDTO;
import com.example.calculator.Service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginandJoinController {
    @Autowired
    private JoinService joinService;
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO dto) {
        joinService.joinProc(dto);
        return "redirect:/login";
    }
}
