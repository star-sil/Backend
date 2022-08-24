package com.example.Dokkaebi.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @RequestMapping(value = "/entrypoint")
    public void entrypointException(){
        throw new ApiException(ExceptionEnum.SecurityInvalidToken);
    }

    @RequestMapping(value = "/accessDenied")
    // 해당 부분에서는 모든 method 가 올 수 있기에 그냥 request 로 범용적으로 받기
    public void accessDeniedException(){
        throw new ApiException(ExceptionEnum.SecurityNoAuthentication);
    }

}
