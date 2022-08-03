package com.example.Dokkaebi.help.Qna;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.help.dto.QnaReqDto;
import com.example.Dokkaebi.help.dto.QnaResDto;
import com.example.Dokkaebi.member.JpaMemberRepo;
import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnaService {
    private final JpaQnaRepo jpaQnaRepo;
    private final JpaMemberRepo jpaMemberRepo;
    private final JpaQnaRegiRepo jpaQnaRegiRepo;
    private final JpaContentRepo jpaContentRepo;
    public List<QnaResDto> getListOfQna(String questionerId){
        List<QnaResDto> responseDtos = new ArrayList<>();
        if(questionerId==null){
            //아이디가 없다면 전체 조회
            List<Qna> qnaList = jpaQnaRepo.findAll();
            for (Qna qna : qnaList) {
                responseDtos.add(new QnaResDto(qna));
            }
        }else{
            //있을 시 해당 유저의 qna 만 조회
            Member questioner = jpaMemberRepo.findByIdentity(questionerId);
            List<Qna> qnaList = jpaQnaRepo.findByQuestioner(questioner);
            for (Qna qna : qnaList) {
                QnaRegi qnaRegi = jpaQnaRegiRepo.findByQna(qna);
                if(qnaRegi==null){
                    responseDtos.add(new QnaResDto(qna));
                }else{
                    responseDtos.add(new QnaResDto(qna,qnaRegi));
                }
            }
        } return responseDtos;
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
    public void reply(QnaReqDto qnaReqDto) throws Exception {
    }
    //클릭하면 확인완료 띄우기
    @Transactional
    public void confirm(QnaReqDto qnaReqDto) throws Exception {
    }
}
