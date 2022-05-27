package com.example.Dokkaebi.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

@Slf4j
@Getter
public class TokenRequestDto {

    private String accessJws;
    private String refreshJws;

    public TokenRequestDto(String accessJws, String refreshJws) {
        this.accessJws = accessJws;
        this.refreshJws = refreshJws;
    }
}
