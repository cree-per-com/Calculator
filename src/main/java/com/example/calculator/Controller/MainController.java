package com.example.calculator.Controller;

import com.example.calculator.DAO.CalculationDataRepository;
import com.example.calculator.DAO.DataDTO;
import com.example.calculator.Entity.CalculationEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        List<DataDTO> mydata = calculationDataRepository.UsersData(username);
        model.addAttribute("mydatalist",mydata);
        model.addAttribute("username",username);
        return "mypage";
    }

    @PostMapping("/liked")
    @ResponseBody
    public ResponseEntity<?> UpdateLiked(@RequestBody DataDTO dataDTO) {
        CalculationEntity calculationEntity = calculationDataRepository.findById(dataDTO.getId())
                .orElseThrow(()-> new EntityNotFoundException("해당하는 id의 내역을 찾을 수 없습니다"));
        calculationEntity.setLiked(true);
        calculationDataRepository.save(calculationEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/liked")
    public String LikedCalcData(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        List<DataDTO> likedData = calculationDataRepository.UsersLikedData(username);
        model.addAttribute("mylikedDataList", likedData);
        model.addAttribute("username", username);
        return "liked";
    }
}
