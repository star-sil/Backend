package com.example.Dokkaebi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // http://localhost:8081/swagger-ui.html 에서 확인가능.
    // https://kim-jong-hyun.tistory.com/49 >> 코드 내에서 사용하는 swagger 예시
    private static final String API_NAME = "도깨비 API";
    private static final String API_VERSION = "0.0.1";
    private static final String API_DESCRIPTION = "도깨비 API 명세서";

//        ParameterBuilder
//        API를 테스트할때 모든 API에 전역 파라미터를 설정한다.
//        해당소스는 모든 API 테스트시 header에 'Autorization' 이라는 값을 추가하도록 했다.
    @Bean
    public Docket api() {
        Parameter accessTokenBuilder = new ParameterBuilder()
                .name("Authorization")
                .description("access_token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        Parameter refreshTokenBuilder = new ParameterBuilder()
                .name("refresh_token")
                .description("refresh_token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        List<Parameter> globalParamters = new ArrayList<>();
        globalParamters.add(accessTokenBuilder);
        globalParamters.add(refreshTokenBuilder);

//        PathSelectors.any()
//        해당 package 하위에 있는 모든 url에 적용시킨다. /test/**로시작되는 부분만 적용해주고싶다면
//        PathSelectors.ant(String pattern) 메서드에 url parttern을 추가해주면된다.
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                // https://stackoverflow.com/questions/30047129/swagger-springfox-always-generates-some-response-messages-401-403-by-defa
                .globalOperationParameters(globalParamters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.Dokkaebi"))
                .build();
    }

    //api 이름 및 버전, 정보를 나타냄
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(API_NAME)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .build();
    }
}