package com.example.Dokkaebi.controller.dtos;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Duration;
import java.util.Date;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String identity;
    private String password;

    @Builder
    public LoginRequestDto(String identity, String password) {
        this.identity = identity;
        this.password = password;
    }

}
