package com.example.Dokkaebi.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Gender gender;
    private Auth auth;

    public void encodePassword(String encodedPassword){
        this.password = encodedPassword;
    }
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
        this.gender = member.getGender();
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
                .gender(gender)
                .auth(auth)
                .build();
    }
}
