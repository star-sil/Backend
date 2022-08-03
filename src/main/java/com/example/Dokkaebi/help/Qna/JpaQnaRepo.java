package com.example.Dokkaebi.help.Qna;

import com.example.Dokkaebi.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaQnaRepo extends JpaRepository<Qna, Long> {
    List<Qna> findByQuestioner(Member questioner);
    List<Qna> findByStatus(QnaStatus status);
}
