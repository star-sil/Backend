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
    private String key;

    @Builder
    public TokenRequestDto(String accessJws, String refreshJws, String key) {
        this.accessJws = accessJws;
        this.refreshJws = refreshJws;
        this.key = key;
    }

    public boolean isValid(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            log.info(e.toString());
            return false;
        }
    }


}
