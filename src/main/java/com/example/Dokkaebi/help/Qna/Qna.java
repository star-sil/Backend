package com.example.Dokkaebi.help.Qna;

import com.example.Dokkaebi.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Qna {
    @Id @GeneratedValue
    @Column(name="qna_id")
    private Long id;
    //참조 시 단계별 엔티티 조회 개념? eager 는 연관 관계를 즉시 가져옴.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member questioner;
    private String title;
    @Enumerated(EnumType.STRING)
    private QnaStatus status;
    private LocalDateTime regiDate;

    @Builder
    public Qna(String title,Member questioner){
        this.regiDate=LocalDateTime.now();
        this.title=title;
        this.status=QnaStatus.REGISTERED;
        this.questioner=questioner;
    }
    public void AdminConfirm(){
        this.status= QnaStatus.COMPLETE;
    }
    public void AdminResponded(){
        this.status= QnaStatus.RESPONDED;
    }

}
