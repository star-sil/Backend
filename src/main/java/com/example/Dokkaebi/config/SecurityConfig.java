package com.example.Dokkaebi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//method security 기능을 사용한다.
//서비스 계층을 직접 호출할때 사용할 수 있는 보안 기능이다.
//Web기반의 Security를 적용했을때는 어울리지 않는 기능이다.
//MethodSecurity용 설정이 따로 필요한데 이때 사용하는것이 @EnableGlobalMethodSecurity이다.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .disable()
                .csrf()
                .disable()
                .cors()
                .and()
                .formLogin()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }



}
