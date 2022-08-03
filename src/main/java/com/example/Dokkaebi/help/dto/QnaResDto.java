package com.example.Dokkaebi.help.dto;

import com.example.Dokkaebi.help.Qna.Qna;
import com.example.Dokkaebi.help.Qna.QnaRegi;
import com.example.Dokkaebi.help.Qna.QnaStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QnaResDto {
    private Long qnaId;
    private String questionerId;
    private String adminId;
    @ApiModelProperty(example = "2022-05-03T18:34:24.458")
    private LocalDateTime regiDate;
    @ApiModelProperty(example = "2022-05-04T18:34:24.458")
    private LocalDateTime replyDate;
    @ApiModelProperty(example = "무언가가 안되는데 이건 어떻게 해결하나요?")
    private String content;
    @ApiModelProperty(example = "그럴 때는 이렇게 저렇게 하시면 됩니다.")
    private String comment;
    @ApiModelProperty(example = "아무리 해봐도 안돼요")
    private String title;
    private QnaStatus status;

    @Builder
    public QnaResDto(Qna qna, QnaRegi qnaRegi){
        this.qnaId=qna.getId();
        this.questionerId=qna.getQuestioner().getIdentity();
        this.regiDate=qna.getRegiDate();
        this.adminId=qnaRegi.getAdmin().getIdentity();
        this.comment=qnaRegi.getComment();
        this.status=qna.getStatus();
        this.replyDate=qnaRegi.getReplyDate();
        this.title=qna.getTitle();
    }

    @Builder
    public QnaResDto(Qna qna){
        this.qnaId=qna.getId();
        this.questionerId=qna.getQuestioner().getIdentity();
        this.regiDate=qna.getRegiDate();
        this.status=qna.getStatus();
        this.title=qna.getTitle();
    }
}
