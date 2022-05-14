package com.example.Dokkaebi.domain;

import com.example.Dokkaebi.common.QnaStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Qna {
    @Id @GeneratedValue
    private Long id;

    @NotNull
    private Long memberId;
    private Long adminId;
    private LocalDateTime regiDate;
    private LocalDateTime repliedDate;
    private String content;
    private String comment;
    @Enumerated(EnumType.ORDINAL)
    private QnaStatus status;

    @Builder
    public Qna(Long memberId, Long adminId,LocalDateTime repliedDate, LocalDateTime regiDate, String content, String comment, QnaStatus status){
        this.memberId=memberId;
        this.adminId=adminId;
        this.regiDate=regiDate;
        this.content=content;
        this.comment=comment;
        this.status=status;
        this.repliedDate=repliedDate;
    }
    public void RegiRepliedAdmin(Long adminId, String comment){
        this.adminId=adminId;
        this.comment=comment;
        this.status=QnaStatus.RESPONDED;
        this.repliedDate=LocalDateTime.now();
    }
    public void AdminConfirm(){
        this.status=QnaStatus.UNDERIVIEW;
    }
}
