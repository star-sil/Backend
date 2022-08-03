package com.example.Dokkaebi.member;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.token.TokenRequestDto;
import com.example.Dokkaebi.token.TokenResponseDto;
import com.example.Dokkaebi.help.dto.MyPageResponse;
import com.example.Dokkaebi.token.Token;
import com.example.Dokkaebi.help.MyPageService;
import com.example.Dokkaebi.token.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {
    @Value("${key.token}")
    private String key;
    private final MyPageService myPageService;
    private final MemberService memberservice;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder; 
    // 비밀번호 암호화를 위해 기본적으로 제공되는 security 내장 인코더
    // 이지만, DokkaebiApplication 의 passwordEncoder 에 의해 새로 주입됨.

    @ApiOperation(value = "회원 가입", notes = "성공하면 memberId 반환")
    @PostMapping("/member/new")
    public ResponseEntity<Long> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        // dto 레벨에서 encoder 를 쓰지는 않으니까..
        memberRequestDto.encodePassword(passwordEncoder.encode(memberRequestDto.getPassword()));
        Member member = memberRequestDto.toEntity();
        Long memberId = memberservice.join(member);
        Token token = Token.builder().member(member).build();
        tokenService.Join(token);

        //객체면 application/json, 문자열이면 text content-type으로 보냄 대신ResponseEntity<Object> object 사용해야함!!
        return new ResponseEntity(memberId, HttpStatus.OK);

    }
    @ApiOperation(value = "로그인", notes = "성공하면 access, refresh 토큰 반환")
    @PostMapping("/member/login")
    public ResponseEntity<TokenResponseDto> loginMember(@RequestBody LoginRequestDto loginRequestDto) {
        Member member = memberservice.findMember2(loginRequestDto.getIdentity());
        // 아이디에 맞는 사람이 없거나
        if(member==null){
            log.error("엑 안맞네");
            throw new ApiException(ExceptionEnum.IdentityNotMatched);
        }else{
            // 인코더를 사용하여 평문 암호와 저장된 암호문을 비교하여 boolean 리턴
            if(passwordEncoder.matches(loginRequestDto.getPassword(),member.getPassword())){
                String accessToken = tokenService.makeAccessJws(member);
                String refreshToken = tokenService.makeRefreshJws();

                //추후 수정 필요..
                TokenResponseDto tokenResponseDto = new TokenResponseDto(accessToken,refreshToken,member);
                Token token = tokenService.findTokenByMember(member);
                //refresh 토큰 업데이트
                token.refresh(refreshToken);
                tokenService.Join(token);
                return ResponseEntity.ok(tokenResponseDto);
            }else {
                log.error("엑 안맞네 여기도 아니야?");
                // 비밀번호 틀리면 예외처리
                throw new ApiException(ExceptionEnum.IdentityNotMatched);
            }
        }
    }
    @ApiOperation(value = "토큰 재발급")
    @PostMapping("/member/reissue")
    @ResponseBody
    public ResponseEntity<Object> reissueToken(
            @RequestHeader(value = "access_token") String accessToken,
            @RequestHeader(value = "refresh_token") String refreshToken,
            @RequestBody String identity) {

        //여기 좀 수정 필요할 수도..
        accessToken = tokenService.refreshProcess(accessToken,refreshToken,identity);
        Member member = memberservice.findMember(identity);

        TokenResponseDto tokenResponseDto = new TokenResponseDto(accessToken,refreshToken,member);
        return new ResponseEntity(new Result(tokenResponseDto), HttpStatus.OK);
    }

    //권한 주기기능은 https://llshl.tistory.com/28?category=942328 참고
    @PostMapping("/member/loginTest")
    @ResponseBody
    public String CheckToken(@RequestHeader(value = "access_token") String accessToken) {
        return "asdf";

    }

    @PutMapping("/member")
    @ResponseBody
    public String modifyInfo(
            //@RequestHeader(value = "access_token")String accessToken,
            //추후 토큰 인증을 통해 정보 변경 권한 확인하기
            @RequestBody MemberRequestDto memberRequestDto){
        memberservice.updateMember(memberRequestDto);
        return "회원 정보가 성공적으로 변경되었습니다.";
    }
    //삭제는 염두해 두지 않음. 탈퇴의 결과는 조금 더 생각해 봐야 할 듯.

    @GetMapping("/mypage/{identity}")
    @ResponseBody
    @ApiOperation(value="마이페이지 조회", notes = "주어진 id로 마이페이지 조회")
    public ResponseEntity<MyPageResponse> viewMyPage(@PathVariable(name = "identity") String identity){
        MyPageResponse myPageResponse = myPageService.viewMyPage(identity);
        return ResponseEntity.ok(myPageResponse);
    }
    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
