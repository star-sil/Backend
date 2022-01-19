package com.example.Dokkaebi.service;

import com.example.Dokkaebi.domain.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @PostMapping("/start")
    public String start(Member member){
        if(member.getMemberName() == "cilab")
            return "server get cilab";
        else
            return "server doesn't get cilab";
    }

}
