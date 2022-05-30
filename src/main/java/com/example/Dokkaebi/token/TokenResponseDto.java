package com.example.Dokkaebi.token;

import com.example.Dokkaebi.member.Auth;
import com.example.Dokkaebi.member.Member;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Getter
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private Auth auth;

    public TokenResponseDto(String accessToken, String refreshToken, Member member) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.auth = member.getAuth();
    }

    public Token toEntity(Member member) {
        return Token.builder()
                .refreshToken(refreshToken)
                .member(member)
                .build();
    }
}

