package com.example.Dokkaebi.help.Qna;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.help.dto.QnaHisDto;
import com.example.Dokkaebi.help.dto.QnaReqDto;
import com.example.Dokkaebi.help.dto.QnaResDto;
import com.example.Dokkaebi.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnaService {
    private final JpaQnaRepo jpaQnaRepo;
    private final JpaContentRepo jpaContentRepo;

    public QnaHisDto getListOfQna(Member questioner){
        QnaHisDto qnaHisDto = new QnaHisDto();
        if(questioner==null){
            //아이디가 없다면 전체 조회
            List<Qna> qnaList = jpaQnaRepo.findAll();
            for (Qna qna : qnaList) {
                qnaHisDto.addQnaResDto(new QnaResDto(qna));
            }
        }else{
            //있을 시 해당 유저의 qna 만 조회
            List<Qna> qnaList = jpaQnaRepo.findByQuestioner(questioner);
            for (Qna qna : qnaList) {
                qnaHisDto.addQnaResDto(new QnaResDto(qna));
            }
        }
        return qnaHisDto;
    }
    @Transactional
    public Qna register(QnaReqDto qnaReqDto, Member questioner){
        Qna qna = new Qna(qnaReqDto.getTitle(), questioner);
        jpaQnaRepo.save(qna);
        Content content = new Content(qnaReqDto.getComment(),WriterStatus.USER,qna);
        jpaContentRepo.save(content);
        return qna;
    }

    @Transactional
    public void reply(QnaReqDto qnaReqDto, Long qnaId) {
        Qna qna = jpaQnaRepo.findById(qnaId)
                .orElseThrow(()->new ApiException(ExceptionEnum.InvalidQnaId));
        Content content = new Content(qnaReqDto.getComment(), WriterStatus.ADMIN, qna);
        jpaContentRepo.save(content);
        qna.AdminResponded();
    }

    public QnaHisDto checkQna(QnaStatus status) {
        QnaHisDto qnaHisDto = new QnaHisDto();
        List<Qna> qnaList = jpaQnaRepo.findByStatus(status);
        for (Qna qna : qnaList) {
            qnaHisDto.addQnaResDto(new QnaResDto(qna));
        }
        return qnaHisDto;
    }
}
