package com.example.Dokkaebi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
//RestControllerAdvice 를 통해서 모든 @Controller, 전역에서 발생할 수 있는 에러를 잡아 처리 가능
//@ControllerAdvice 및 @ResponseBody 을 포함하고 있어 객체를 리턴할 수도 있음.
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> exceptionHandler(final ApiException e){
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        log.error(e.getMessage(),stackTraceElements[0]);
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ExceptionResponse.builder()
                        .errorCode(e.getError().getCode())
                        .description(e.getMessage())
                        .build());
    }
}
