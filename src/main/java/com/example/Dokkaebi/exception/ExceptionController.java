package com.example.Dokkaebi.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {
    @GetMapping(value = "/entrypoint")
    public void entrypointException(){
        throw new ApiException(ExceptionEnum.SecurityInvalidToken);
    }

    @GetMapping(value = "/accessDenied")
    public void accessDeniedException(){
        throw new ApiException(ExceptionEnum.SecurityNoAuthentication);
    }

}
