package com.example.Dokkaebi.help.Qna;

import com.example.Dokkaebi.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class QnaRegi {
    @Id @GeneratedValue
    @Column(name = "qnaRegi_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member admin;
    @ManyToOne
    @JoinColumn(name="qna_id")
    private Qna qna;
    private LocalDateTime replyDate;
    private String comment;

    @Builder
    public QnaRegi(Member admin, Qna qna, LocalDateTime replyDate,String comment){
        this.admin=admin;
        this.comment=comment;
        this.replyDate=replyDate;
        this.qna=qna;
    }
}
