package com.example.Dokkaebi.help.dto;

import com.example.Dokkaebi.help.Qna.Content;
import com.example.Dokkaebi.help.Qna.Qna;
import com.example.Dokkaebi.help.Qna.QnaStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

@Getter
public class QnaResDto {
    @ApiModelProperty(example = "1")
    private Long qnaId;
    @ApiModelProperty(example = "아무리 해봐도 안돼요")
    private String title;
    @ApiModelProperty(example = "김아무개")
    private String writer;
    @ApiModelProperty(example = "문의 내용 및 답변 내용, 문의 및 답변 시간, 작성자 지위")
    private List<Content> contents;
    private QnaStatus status;

    @Builder
    public QnaResDto(Qna qna){
        this.qnaId=qna.getId();
        this.title=qna.getTitle();
        this.writer=qna.getQuestioner().getIdentity();
        this.contents=qna.getContents();
        this.status=qna.getStatus();
    }
}
