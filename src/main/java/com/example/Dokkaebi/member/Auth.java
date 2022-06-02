package com.example.Dokkaebi.member;

import lombok.Getter;

@Getter
public enum Auth {
    ADMIN("ADMIN"),
    USER("MEMBER");
    private String role;
    private Auth(String role){
        this.role=role;
    }
}
