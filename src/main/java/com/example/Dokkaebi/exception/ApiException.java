package com.example.Dokkaebi.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private ExceptionEnum error;
    public ApiException(ExceptionEnum e){
        super(e.getDescription());
        this.error=e;
    }
}
