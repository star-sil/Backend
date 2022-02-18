package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Token;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Getter
public class TokenResponseDto {
    private String accessJws;
    private String refreshJws;



    public TokenResponseDto(String identity, String key) {
        this.accessJws = makeAccessJws(identity,key);
        this.refreshJws = makeAccessJws("", key);

    }

    public Token toEntity() {
        return Token.builder()
                .refreshToken(refreshJws)
                .build();
    }

    private String makeAccessJws(String identity, String key) {
        Date now = new Date();
        String encodedString = Base64.getEncoder().encodeToString(key.getBytes());

        if(identity.isEmpty()){
            return Jwts.builder()
                    .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + Duration.ofMinutes(Integer.valueOf(30)).toMillis()))
                    .claim("userIdentity",identity)
                    .signWith(SignatureAlgorithm.HS256,encodedString)
                    .compact();
        }
        else {
            return Jwts.builder()
                    .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + Duration.ofMinutes(Integer.valueOf(5)).toMillis()))
                    .claim("userIdentity",identity)
                    .signWith(SignatureAlgorithm.HS256,encodedString)
                    .compact();
        }

    }

}
