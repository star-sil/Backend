package com.example.Dokkaebi.help.Qna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQnaRegiRepo extends JpaRepository<QnaRegi, Long>{
    QnaRegi findByQna(Qna qna);
}
