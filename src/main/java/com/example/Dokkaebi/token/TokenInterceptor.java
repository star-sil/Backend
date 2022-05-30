package com.example.Dokkaebi.token;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenInterceptor implements HandlerInterceptor {
    private final TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String accessToken = request.getHeader("access_token");
        if(accessToken==null){
            throw new ApiException(ExceptionEnum.InvalidToken);
        }
        log.info("authorization: "+accessToken);
        if(!tokenService.accessTokenCheck(accessToken)){
            throw new ApiException(ExceptionEnum.InvalidToken);
        }else{
            return true;
        }
    }
}
