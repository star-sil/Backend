package com.example.Dokkaebi.help.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QnaReqDto {
    @ApiModelProperty(example = "그럴 때는 이렇게 저렇게 하시면 됩니다.")
    private String comment;
    @ApiModelProperty(example = "아무리 해봐도 안돼요")
    private String title;
}
