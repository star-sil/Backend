package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.response.MyPageResponse;
import com.example.Dokkaebi.service.MyPageService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/{id}")
    @ApiOperation(value="마이페이지 조회", notes = "주어진 id로 마이페이지 조회")
    public ResponseEntity<MyPageResponse> viewMyPage(@PathVariable Long memberId){
        MyPageResponse myPageResponse = myPageService.viewMyPage(memberId);
        return ResponseEntity.ok(myPageResponse);
    }
}
