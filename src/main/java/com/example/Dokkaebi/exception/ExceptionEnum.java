package com.example.Dokkaebi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//Enum 으로 관리하게 되면
@Getter
public enum ExceptionEnum {
    TestException(HttpStatus.BAD_REQUEST,101,"test Exception");

    private HttpStatus status;
    private int code;
    private String description;

    private ExceptionEnum(HttpStatus status,int code, String description){
        this.code=code;
        this.status=status;
        this.description=description;
    }
}
