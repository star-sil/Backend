package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Token;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Dokkaebi project + CI/CD test success!!\n" + new Date() ;
    }

}
