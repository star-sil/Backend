package com.example.Dokkaebi.Repository;

import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Token;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TokenRepository {
    private final EntityManager em;

    //getSingleList()는 정확히 값이 하나가 아니면 오류로 된다.
    public List<Token> findByRefreshToken(String refreshToken) {
        return em.createQuery("select t from Token t where t.refreshToken = :refreshToken", Token.class)
                .setParameter("refreshToken", refreshToken)
                .getResultList();
    }

    public void save(Token token) {
        em.persist(token);
    }

}
