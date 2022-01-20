package com.example.Dokkaebi.service;

import com.example.Dokkaebi.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@Slf4j
public class MainController {
//    @PostMapping("/start")
//    public String start(Member member){
//        log.info(member.getId());
//        if(member.getId() == "cilab")
//            return "success";
//        else
//            return "fail";
//    }
    @ResponseBody
    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public String test(@RequestBody Member member) {
        log.info(member.getId());
        if(member.getId().equals("cilab"))
            return "success";
        else
            return "fail";
    }
}
