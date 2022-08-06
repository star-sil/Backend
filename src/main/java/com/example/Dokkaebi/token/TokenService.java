package com.example.Dokkaebi.token;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.member.JpaMemberRepo;
import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Transactional(readOnly = true) //읽기만 하는 곳에 쓰기기
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository TokenRepo;
    private final JpaMemberRepo jpaMemberRepo;
    private final JpaTokenRepo jpaTokenRepo;
    private final MemberService memberService;

    @Value("${key.token}")
    private String key;
    @Value("${validation.access}")
    private int accessValidTime;
    @Value("${validation.refresh}")
    private int refreshValidTime;

    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }
    public Token findTokenByMember(Member member){
        return jpaTokenRepo.findByMember(member);
    }

    @Transactional //위에랑 달리 읽는게 아니기 때문에 붙여줘야한다
    public void Join(Token token) {
        TokenRepo.save(token);
    }

    public String makeAccessJws(Member member){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(Integer.valueOf(accessValidTime)).toMillis()))
                .claim("userIdentity",member.getIdentity())
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }
    // 토큰으로 인증 조회
    public Authentication getAuthentication(String accessToken){
        List Authorizes = new ArrayList();
        String identity = this.getIdentityFromToken(accessToken);
        Member member = (Member) memberService.loadUserByUsername(identity);
        // userDetails 클래스 형태의 Member 를 가져와서 형 변환 해준다.

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(member.getAuth().getRole());
        Authorizes.add(simpleGrantedAuthority);
        return new UsernamePasswordAuthenticationToken(member,"",Authorizes);
    }

    public String getIdentityFromToken(String accessToken){
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(accessToken);
            return claims.getBody().get("userIdentity", String.class);
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new ApiException(ExceptionEnum.TokenMalformed);
        }catch (ExpiredJwtException e){
            throw new ApiException(ExceptionEnum.SecurityInvalidToken);
        }
    }
    //?? 위랑 밑 합치기.. 나중에
    public boolean accessTokenCheck(String accessToken) {
        try{
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(accessToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new ApiException(ExceptionEnum.TokenMalformed);
        }catch (ExpiredJwtException e){
            return false;
        }
    }

    public String makeRefreshJws(){
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofMinutes(Integer.valueOf(refreshValidTime)).toMillis()))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }

    public boolean refreshTokenCheck(String refreshToken){
        return accessTokenCheck(refreshToken);
    }

    public String refreshProcess(String refreshToken) {
        Token token = jpaTokenRepo.findByRefreshToken(refreshToken);
        Member member = token.getMember();
        if(member==null){
            throw new ApiException(ExceptionEnum.IdentityNotMatched);
        }else{
            if(refreshTokenCheck(refreshToken)){
                //access 만료, refresh 유효. access 재발급
                String newAccessToken = makeAccessJws(member);
                return newAccessToken;
            }else{
                // 둘 다 만료. 재 로그인 필요.
                throw new ApiException(ExceptionEnum.NeedSignInAgain);
            }
        }
    }
}

