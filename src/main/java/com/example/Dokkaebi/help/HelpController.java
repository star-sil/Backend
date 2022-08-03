package com.example.Dokkaebi.help;

import com.example.Dokkaebi.help.Qna.Qna;
import com.example.Dokkaebi.help.Qna.QnaService;
import com.example.Dokkaebi.help.dto.QnaHisDto;
import com.example.Dokkaebi.help.dto.QnaReqDto;
import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.token.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class HelpController {
    //서비스 내 편의기능을 총괄하는 Controller
    private final QnaService qnaService;
    private final MemberService memberService;
    private final TokenService tokenService;

    @GetMapping("/help/qna")
    @ApiOperation(value = "문의사항 전체 조회", notes = "모든 문의사항을 조회함. 시간 순서 추가 필요")
    public ResponseEntity<QnaHisDto> findQnaById(@RequestHeader(name = "access_token") String accessToken) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        return ResponseEntity.ok(qnaService.getListOfQna(member));
    }
    @PostMapping("/help/qna")
    @ApiOperation(value = "문의사항 등록", notes = "주어진 title와 comment 새로운 문의사항을 등록합니다.")
    public ResponseEntity<Long> registerQna(
            @RequestBody QnaReqDto qnaReqDto,
            @RequestHeader(value = "access_token") String accessToken){
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member questioner = memberService.findMember(identity);
        Qna qna = qnaService.register(qnaReqDto,questioner);
        return ResponseEntity.ok(qna.getId());
    }

    @PostMapping("/help/qna/{qnaId}")
    @ApiOperation(value = "문의사항 답변하기", notes = "주어진 admin id와 comment로 답변을 작성합니다.")
    public ResponseEntity<Long> replyQna(@RequestBody QnaReqDto qnaReqDto, String qnaId) throws Exception {
        qnaService.reply(qnaReqDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/help/qna")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "관리자 문의사항 조회 시", notes = "관리자가 문의사항 조회 시 호출")
    public ResponseEntity confirmQna(@RequestBody QnaReqDto qnaReqDto) throws Exception {
        qnaService.confirm(qnaReqDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @Data
    @AllArgsConstructor
    static class QnaResult<T>{
        private T qnaResult;
    }
}
