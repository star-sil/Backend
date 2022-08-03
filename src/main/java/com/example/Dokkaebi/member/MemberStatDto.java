package com.example.Dokkaebi.member;

import com.example.Dokkaebi.token.Token;
import lombok.Getter;

@Getter
public class MemberStatDto {
    private String identity;
    private Auth auth;
    public MemberStatDto(Member member) {
        this.identity = member.getIdentity();
        this.auth = member.getAuth();
    }
}
