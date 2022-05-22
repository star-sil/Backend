package com.example.Dokkaebi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Dokkaebi project + CI/CD test success!!\n" + new Date() ;
    }

}
