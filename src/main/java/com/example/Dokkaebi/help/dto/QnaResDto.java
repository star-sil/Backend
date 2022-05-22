package com.example.Dokkaebi.help.dto;

import com.example.Dokkaebi.help.Qna;
import com.example.Dokkaebi.help.QnaStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QnaResDto {
    private Long memberId;
    private Long adminId;
    @ApiModelProperty(example = "2022-05-03")
    private LocalDateTime regiDate;
    @ApiModelProperty(example = "무언가가 안되는데 이건 어떻게 해결하나요?")
    private String content;
    @ApiModelProperty(example = "그럴 때는 이렇게 저렇게 하시면 됩니다.")
    private String comment;
    private QnaStatus status;

    @Builder
    public QnaResDto(Qna qna){
        this.memberId=qna.getMemberId();
        this.regiDate=qna.getRegiDate();
        this.content=qna.getContent();
        this.adminId=qna.getAdminId();
        this.comment=qna.getComment();
        this.status=qna.getStatus();
    }
}
