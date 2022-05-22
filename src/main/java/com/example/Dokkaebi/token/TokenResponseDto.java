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



    public TokenResponseDto(Member member, String key) {
        this.accessToken = makeAccessJws(member,key);
        this.refreshToken = makeRefreshJws(key);
        this.auth = member.getAuth();

    }

    public Token toEntity() {
        return Token.builder()
                .refreshToken(refreshToken)
                .build();
    }

    private String makeAccessJws(Member member, String key) {
        Date now = new Date();
        String encodedString = Base64.getEncoder().encodeToString(key.getBytes());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(Integer.valueOf(30)).toMillis()))
                .claim("userIdentity",member.getIdentity())
                .signWith(SignatureAlgorithm.HS256,encodedString)
                .compact();
    }

    private String makeRefreshJws(String key) {
        Date now = new Date();
        String encodedString = Base64.getEncoder().encodeToString(key.getBytes());

            return Jwts.builder()
                    .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + Duration.ofMinutes(Integer.valueOf(5)).toMillis()))
                    .signWith(SignatureAlgorithm.HS256,encodedString)
                    .compact();
        }
}

