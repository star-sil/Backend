package com.example.Dokkaebi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String identity;
    private String password;
    private String city;
    private String street;
    private String phone;
    private String email;
    private String birth;

    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Enumerated(EnumType.STRING)
    private Auth auth;

    @Builder
    public Member(String name, String identity, String password, String city, String street, String phone, String email, String birth, Sex sex, Auth auth) {
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
}
