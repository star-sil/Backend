package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.QnaReqDto;
import com.example.Dokkaebi.controller.dtos.QnaResDto;
import com.example.Dokkaebi.domain.Qna;
import com.example.Dokkaebi.service.QnaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelpController {
    //서비스 내 편의기능을 총괄하는 Controller
    private final QnaService qnaService;

    @GetMapping("/help/qna/")
    @ApiOperation(value = "문의사항 전체 조회", notes = "모든 문의사항을 조회함. 시간 순서 추가 필요")
    public ResponseEntity<List<QnaResDto>> findQna() {
        List<QnaResDto> responseDtos = new ArrayList<>();
        List<Qna> qnaes = qnaService.findAll();
        for (Qna qna : qnaes) {
            responseDtos.add(new QnaResDto(qna));
        }
        return ResponseEntity.ok(responseDtos);
    }
    @PostMapping("/help/qna/")
    @ApiOperation(value = "문의사항 등록", notes = "주어진 member id와 content로 새로운 문의사항을 등록합니다.")
    public ResponseEntity<Long> registerQna(@RequestBody QnaReqDto qnaReqDto){
        Qna qna = qnaService.register(qnaReqDto);
        return ResponseEntity.ok(qna.getId());
    }

    @PostMapping("/help/qna/reply")
    @ApiOperation(value = "문의사항 답변하기", notes = "주어진 admin id와 comment로 답변을 작성합니다.")
    public ResponseEntity<Long> replyQna(@RequestBody QnaReqDto qnaReqDto) throws Exception {
        qnaService.reply(qnaReqDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/help/qna/")
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
