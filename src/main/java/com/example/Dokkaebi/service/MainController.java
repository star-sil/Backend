package com.example.Dokkaebi.service;

import com.example.Dokkaebi.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@Slf4j
public class MainController {
//    이 방식은 요청이 폼으로 날라올때 사용할 수 있다. 하지만 지금은 json방식으로 요청을
//    받아야 되기 때문에 밑에처럼한다.
//    @PostMapping("/start")
//    public String start(Member member){
//        log.info(member.getId());
//        if(member.getId() == "cilab")
//            return "success";
//        else
//            return "fail";
//    }

//  ResponseBody는 폼이 아닌 api방식으로 요청을 받을때 http 바디부분의 데이터를 그대로
//  내려받는 의미를 가진다. 따라서 알맞게 데이터를 맞춰줘야하고 쿼리로 날라올때는 @Param을 사용한다.
    @ResponseBody
    @PostMapping(value = "/start")
    public String test(@RequestBody Member member) {
        log.info(member.getId());
        if(member.getId().equals("cilab"))
            return "success";
        else
            return "fail";
    }
}
