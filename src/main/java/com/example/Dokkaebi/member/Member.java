package com.example.Dokkaebi.member;

import com.example.Dokkaebi.token.Token;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Entity
@NoArgsConstructor
public class Member implements UserDetails {
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

    @Override
    // 원래 해당 레벨에서 여러개의 인증 권한을 가질 수 있는 경우를 위해 collection 을 사용
    // 우선 그런 경우를 상정하지 않고 TokenService 에서 직접 해당 객체를 만들어 사용하는 것으로 대체
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.identity;
    }

    @Override
    //계정의 만료 여부 리턴
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    //계정의 잠금 여부 리턴
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    //비밀번호 만료 여부 리턴
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    //계정 활성화 여부 리턴
    public boolean isEnabled() {
        return false;
    }
    /*
    * 위 내용들은 필요할 시에 추가적으로 구현해 주면 될 것 같기도.. 하고
    * */
}
