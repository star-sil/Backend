package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.common.QnaStatus;
import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Qna;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QnaReqDto {
    @ApiModelProperty(example = "2")
    private Long qnaId;
    @ApiModelProperty(example = "1")
    private Long memberId;
    @ApiModelProperty(example = "3")
    private Long adminId;
    @ApiModelProperty(example = "2022-05-03T18:34:24.458")
    private LocalDateTime regiDate;

    @ApiModelProperty(example = "2022-05-12T18:34:24.458")
    private LocalDateTime repliedDate;
    @ApiModelProperty(example = "무언가가 안되는데 이건 어떻게 해결하나요?")
    private String content;
    @ApiModelProperty(example = "그럴 때는 이렇게 저렇게 하시면 됩니다.")
    private String comment;
    private QnaStatus status;

    public Qna toEntity(){
        return Qna.builder()
                .content(content)
                .memberId(memberId)
                .adminId(adminId)
                .comment(comment)
                .status(status)
                .regiDate(regiDate)
                .repliedDate(repliedDate)
                .build();
    }
}
