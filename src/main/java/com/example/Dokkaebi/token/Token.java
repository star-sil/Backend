package com.example.Dokkaebi.token;

import com.example.Dokkaebi.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Token {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;
    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;
    private String refreshToken;

    @Builder
    public Token(String refreshToken,Member member) {
        this.member=member;
        this.refreshToken = refreshToken;
    }

    public void refresh(String refreshToken){
        this.refreshToken = refreshToken;
    }
}
