package com.example.Dokkaebi.token;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Token {

    @Id @GeneratedValue
    private Long id;

    private String refreshToken;

    @Builder
    public Token(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
