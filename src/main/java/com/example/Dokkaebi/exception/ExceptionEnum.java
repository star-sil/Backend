package com.example.Dokkaebi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//Enum 으로 관리하게 되면
@Getter
public enum ExceptionEnum {
    TestException(HttpStatus.BAD_REQUEST,101,"test Exception"),
    IdentityNotMatched(HttpStatus.UNAUTHORIZED,103,"존재하지 않는 아이디입니다."),
    InvalidToken(HttpStatus.UNAUTHORIZED,101,"Access Token이 만료되었습니다."),
    TokenMalformed(HttpStatus.UNAUTHORIZED,104,"지원하지 않는 형태의 토큰이 입력되었습니다."),
    NeedSignInAgain(HttpStatus.UNAUTHORIZED,102,"재 로그인이 필요합니다.");
    private HttpStatus status;
    private int code;
    private String description;

    private ExceptionEnum(HttpStatus status,int code, String description){
        this.code=code;
        this.status=status;
        this.description=description;
    }
}
