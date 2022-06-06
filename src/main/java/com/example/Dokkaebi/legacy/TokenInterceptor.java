//package com.example.Dokkaebi.legacy;
//
//import com.example.Dokkaebi.exception.ApiException;
//import com.example.Dokkaebi.exception.ExceptionEnum;
//import com.example.Dokkaebi.token.TokenService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class TokenInterceptor implements HandlerInterceptor {
//    private final TokenService tokenService;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//        String accessToken = request.getHeader("access_token");
//        //https://velog.io/@suhongkim98/jwt-interceptor-CORS-preflight-%EC%9D%B4%EC%8A%88-%ED%95%B4%EA%B2%B0%ED%95%98%EA%B8%B0
//        if(request.getMethod().equals("OPTIONS")) {
//            return true;
//        }
//        if(accessToken==null){
//            throw new ApiException(ExceptionEnum.InvalidToken);
//        }
//        log.info("authorization: "+accessToken);
//        if(!tokenService.accessTokenCheck(accessToken)){
//            throw new ApiException(ExceptionEnum.InvalidToken);
//        }else{
//            return true;
//        }
//    }
//}
