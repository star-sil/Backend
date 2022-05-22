package com.example.Dokkaebi.help;

import com.example.Dokkaebi.help.dto.QnaReqDto;
import com.example.Dokkaebi.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepo;
    private final MemberRepository memberRepository;
    public List<Qna> findAll() {return qnaRepo.findAll();}
    @Transactional
    public Qna register(QnaReqDto qnaReqDto){
        qnaReqDto.setRegiDate(LocalDateTime.now());
        Qna qna = qnaReqDto.toEntity();
        qnaRepo.save(qna);
        return qna;
    }

    @Transactional
    public void reply(QnaReqDto qnaReqDto) throws Exception {
        Qna qna = qnaRepo.findByQnaId(qnaReqDto.getQnaId());
        qna.RegiRepliedAdmin(qnaReqDto.getAdminId(),qnaReqDto.getComment());
        qnaRepo.save(qna);
    }

    @Transactional
    public void confirm(QnaReqDto qnaReqDto) throws Exception {
        Qna qna = qnaRepo.findByQnaId(qnaReqDto.getQnaId());
        qna.AdminConfirm();
        qnaRepo.save(qna);
    }
}
