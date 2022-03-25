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
    public MemberRequestDto(Member member) {
        this.name = member.getName();
        this.identity = member.getIdentity();
        this.password = member.getPassword();
        this.city = member.getCity();
        this.street = member.getStreet();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.birth = member.getBirth();
        this.sex = member.getSex();
        this.auth = member.getAuth();
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
