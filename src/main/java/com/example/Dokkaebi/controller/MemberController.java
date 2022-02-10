package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberservice;

    @PostMapping("/member/new")
    public ResponseEntity<Object> createMember(@RequestBody MemberRequestDto memberrequestdto) {
        Member member = memberrequestdto.toEntity();
        Long memberId = memberservice.join(member);
        if(memberId == -1L) {
            return new ResponseEntity("duplicate identity", HttpStatus.BAD_REQUEST);
        }
        //객체면 application/json, 문자열이면 text content-type으로 보냄 대신ResponseEntity<Object> object 사용해야함!!
        return new ResponseEntity(memberId,HttpStatus.OK);
    }

}
