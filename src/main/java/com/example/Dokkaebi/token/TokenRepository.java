package com.example.Dokkaebi.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
