package com.example.calculator.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((x)->x.disable());
        http.authorizeHttpRequests(a-> a
                .requestMatchers("/four-basic-calc"
                        ,"/bi-calc","scientific-calc","exchange-rate-calc").permitAll()
                .requestMatchers("/four-basic-calc/calcProc"
                        ,"/bi-calc/calcProc","scientific-calc/calcProc","exchange-rate-calc/calcProc").permitAll()
                .requestMatchers("/","/login", "/loginProc", "/join", "/joinProc").permitAll()
                .requestMatchers("/mypage","/settings").hasRole("USER")
                .anyRequest().authenticated());
        http.formLogin(au->au.loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                .loginProcessingUrl("/loginProc")
                .permitAll());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}
}
