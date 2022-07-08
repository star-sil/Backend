package com.example.Dokkaebi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//Enum 으로 관리하게 되면
@Getter
public enum ExceptionEnum {
    IdentityNotMatched(HttpStatus.UNAUTHORIZED,103,"존재하지 않는 아이디입니다."),
    TokenMalformed(HttpStatus.UNAUTHORIZED,104,"지원하지 않는 형태의 토큰이 입력되었습니다."),
    NeedSignInAgain(HttpStatus.UNAUTHORIZED,102,"재 로그인이 필요합니다."),
    RentalNotMatched(HttpStatus.BAD_REQUEST,101,"대여중이 아닙니다."),
    DriveLogNotMatched(HttpStatus.BAD_REQUEST,105,"해당하는 주행기록이 없습니다"),
    NotExistAvailableScooter(HttpStatus.BAD_REQUEST, 106, "대여가능한 스쿠터가 존재하지 않습니다."),

    InvalidQnaId(HttpStatus.UNPROCESSABLE_ENTITY,201,"qnd id에 맞는 문의사항이 존재하지 않습니다."),
    SecurityTokenException(HttpStatus.UNAUTHORIZED,301,"auth 단계의 인증 실패"),
    SecurityInvalidToken(HttpStatus.UNAUTHORIZED,302,"Access Token이 만료되었습니다."),
    SecurityNoAuthentication(HttpStatus.UNAUTHORIZED,303,"접근 권한이 없습니다.");


    private HttpStatus status;
    private int code;
    private String description;

    private ExceptionEnum(HttpStatus status,int code, String description){
        this.code=code;
        this.status=status;
        this.description=description;
    }
}
