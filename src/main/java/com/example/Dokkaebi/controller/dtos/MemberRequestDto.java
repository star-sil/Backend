package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Auth;
import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String identity;
    private String password;
    private String city;
    private String street;
    private String phone;
    private String email;
    private String birth;
    private Sex sex;
    private Auth auth;

    @Builder
    public MemberRequestDto(String name, String identity, String password, String city, String street, String phone, String email, String birth, Sex sex, Auth auth) {
        this.name = name;
        this.identity = identity;
        this.password = password;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.sex = sex;
        this.auth = auth;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .identity(identity)
                .password(password)
                .city(city)
                .street(street)
                .phone(phone)
                .email(email)
                .birth(birth)
                .sex(sex)
                .auth(auth)
                .build();
    }
}
