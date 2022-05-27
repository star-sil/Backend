package com.example.Dokkaebi.member;

import com.example.Dokkaebi.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue @Column(name="member_id")
    private Long id;
    private String name;
    @Column(unique = true)
    private String identity;
    private String password;
    private String city;
    private String street;
    private String phone;
    private String email;
    private String birth;

    @OneToOne
    @JoinColumn(name="token_id")
    private Token token;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Auth auth;

    public void updateFromRequest(MemberRequestDto memberRequestDto){
        this.name = memberRequestDto.getName();
        this.identity = memberRequestDto.getIdentity();
        this.password = memberRequestDto.getPassword();
        this.city = memberRequestDto.getCity();
        this.street = memberRequestDto.getStreet();
        this.phone = memberRequestDto.getPhone();
        this.email = memberRequestDto.getEmail();
        this.birth = memberRequestDto.getBirth();
        this.gender = memberRequestDto.getGender();
        this.auth = memberRequestDto.getAuth();
    }
    @Builder
    public Member(String name, String identity, String password, String city, String street, String phone, String email, String birth, Gender gender, Auth auth) {
        this.name = name;
        this.identity = identity;
        this.password = password;
        this.city = city;
        this.street = street;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.auth = auth;
    }
}
