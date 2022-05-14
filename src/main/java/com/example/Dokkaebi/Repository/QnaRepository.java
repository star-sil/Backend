package com.example.Dokkaebi.Repository;

import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Qna;
import com.example.Dokkaebi.domain.ScooterState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QnaRepository {
    private final EntityManager em;
    public void save(Qna qna) {em.persist(qna);}

    public List<Qna> findAll(){
        return em.createQuery("select q from Qna q",Qna.class)
                .getResultList();
    }

    public Qna findByQnaId(Long id) throws Exception {
        List<Qna> qnaes = em.createQuery("select q from Qna q where q.id = :id", Qna.class)
                .setParameter("id", id)
                .getResultList();
        if(qnaes.isEmpty()){
            throw new Exception("id에 맞는 문의사항이 존재하지 않습니다.");
        }
        else{
            return qnaes.get(0);
        }
    }
}
