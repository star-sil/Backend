package com.example.Dokkaebi.help.dto;

import com.example.Dokkaebi.help.Qna.Qna;
import com.example.Dokkaebi.help.Qna.QnaStatus;
import com.example.Dokkaebi.member.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QnaReqDto {
    @ApiModelProperty(example = "2")
    private Long qnaId;
    @ApiModelProperty(example = "questioner")
    private String questionerId;
    @ApiModelProperty(example = "admin")
    private String adminId;
    @ApiModelProperty(example = "2022-05-03T18:34:24.458")
    private LocalDateTime regiDate;
    @ApiModelProperty(example = "2022-05-12T18:34:24.458")
    private LocalDateTime repliedDate;
    @ApiModelProperty(example = "무언가가 안되는데 이건 어떻게 해결하나요?")
    private String content;
    @ApiModelProperty(example = "그럴 때는 이렇게 저렇게 하시면 됩니다.")
    private String comment;
    @ApiModelProperty(example = "아무리 해봐도 안돼요")
    private String title;
    private QnaStatus status;

    public Qna toEntity(Member questioner){
        return Qna.builder()
                .content(content)
                .questioner(questioner)
                .status(QnaStatus.REGISTERD)
                .title(title)
                .regiDate(regiDate)
                .build();
    }
}
