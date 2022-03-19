package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.LoginRequestDto;
import com.example.Dokkaebi.controller.dtos.MemberRequestDto;
import com.example.Dokkaebi.controller.dtos.TokenRequestDto;
import com.example.Dokkaebi.controller.dtos.TokenResponseDto;
import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Token;
import com.example.Dokkaebi.service.MemberService;
import com.example.Dokkaebi.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    @Value("${key.token}")
    private String key;

    private final MemberService memberservice;
    private final TokenService tokenService;

    @PostMapping("/member/new")
    public ResponseEntity<Object> createMember(@RequestBody MemberRequestDto memberrequestdto) {
        Member member = memberrequestdto.toEntity();
        Long memberId = memberservice.join(member);
        if (memberId == -1L) {
            return new ResponseEntity("duplicate identity", HttpStatus.BAD_REQUEST);
        }
        //객체면 application/json, 문자열이면 text content-type으로 보냄 대신ResponseEntity<Object> object 사용해야함!!
        return new ResponseEntity(memberId, HttpStatus.OK);
    }

    @PostMapping("/member/login")
    public ResponseEntity<Object> loginMember(@RequestBody LoginRequestDto loginRequestDto) {
        List<Member> members = memberservice.findMember(loginRequestDto.getIdentity());
        if(members.isEmpty()) return new ResponseEntity("not exist identity", HttpStatus.BAD_REQUEST);
        else{
            TokenResponseDto tokenResponseDto = new TokenResponseDto(members.get(0).getIdentity(),key);
            tokenService.Join(tokenResponseDto.toEntity());
            return new ResponseEntity(new Result(tokenResponseDto),HttpStatus.OK);
        }
    }

    @PostMapping("/member/reissue")
    @ResponseBody
    public ResponseEntity<Object> reissueToken(@RequestHeader(value = "access_token") String accessToken, @RequestHeader(value = "refresh_token") String refreshToken) {
        TokenRequestDto tokenRequestDto = new TokenRequestDto(accessToken, refreshToken,key);
        if(tokenRequestDto.isValid(refreshToken)){
            List<Token> tokens = tokenService.findToken(refreshToken);
            if(tokens.isEmpty()) return new ResponseEntity("invalid refreshToken", HttpStatus.BAD_REQUEST);
            else{
                String identityOfToken = tokenService.encodeToken(accessToken);
                TokenResponseDto tokenResponseDto = new TokenResponseDto(identityOfToken,key);
                return new ResponseEntity(new Result(tokenResponseDto), HttpStatus.OK);

            }
        } else return new ResponseEntity("invalid Token", HttpStatus.BAD_REQUEST);
    }

    //권한 주기기능은 https://llshl.tistory.com/28?category=942328 참고
    @PostMapping("/member/loginTest")
    @ResponseBody
    public String CheckToken(@RequestHeader(value = "access_token") String accessToken) {
        return "asdf";

    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
