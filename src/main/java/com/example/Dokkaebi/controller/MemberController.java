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
    public ResponseEntity createMember(@RequestBody MemberRequestDto memberrequestdto) {
        Member member = memberrequestdto.toEntity();
        Long memberId = memberservice.join(member);
        if(memberId == -1L) {
            return new ResponseEntity("duplicate identity", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(memberId.toString(),HttpStatus.OK);
    }

}
