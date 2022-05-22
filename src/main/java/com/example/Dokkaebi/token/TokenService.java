package com.example.Dokkaebi.token;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true) //읽기만 하는 곳에 쓰기기
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository TokenRepo;

    @Value("${key.token}")
    private String key;

    public List<Token> findToken(String refreshToken) {
        return TokenRepo.findByRefreshToken(refreshToken);
    }

    @Transactional //위에랑 달리 읽는게 아니기 때문에 붙여줘야한다
    public void Join(Token token) {
        TokenRepo.save(token);
    }


    public String encodeToken(String accessToken) {
        String identity = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(accessToken).getBody().get("userIdentity", String.class);
        return identity;
    }
}

