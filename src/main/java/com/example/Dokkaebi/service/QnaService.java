package com.example.Dokkaebi.service;

import com.example.Dokkaebi.Repository.MemberRepository;
import com.example.Dokkaebi.Repository.QnaRepository;
import com.example.Dokkaebi.controller.dtos.QnaReqDto;
import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Qna;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
