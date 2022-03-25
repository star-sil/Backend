package com.example.Dokkaebi.Repository;

import com.example.Dokkaebi.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //getSingleList()는 정확히 값이 하나가 아니면 오류로 된다.
    public Member findByIdentity(String identity) {
        List<Member> members = em.createQuery("select m from Member m where m.identity = :identity", Member.class)
                .setParameter("identity", identity)
                .getResultList();
        if(members.isEmpty()){
            return new Member();
        }
        else{
            return members.get(0);
        }
    }
}
