package com.example.Dokkaebi.help.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QnaHisDto {
    private List<QnaResDto> qnaHistory = new ArrayList<>();

    public void addQnaResDto(QnaResDto qnaResDto) {
        this.qnaHistory.add(qnaResDto);
    }
}
