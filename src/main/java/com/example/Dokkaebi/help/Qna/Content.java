package com.example.Dokkaebi.help.Qna;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Content {
    @Id @GeneratedValue
    private Long id;
    private String comment;
    private LocalDateTime date;
    private WriterStatus writer;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "qna_id")
    private Qna qna;

    public Content(String comment, WriterStatus writer, Qna qna) {
        this.comment = comment;
        this.date = LocalDateTime.now();
        this.writer = writer;
        this.qna = qna;
    }
}
