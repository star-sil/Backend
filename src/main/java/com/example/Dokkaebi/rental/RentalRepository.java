package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalRepository {
    private final EntityManager em;

    public void save(Rental rental){
        em.persist(rental);
    }

    public Optional<Rental> findOneRentalByMember(Member member) {
        return em.createQuery("select r from Rental r where r.member = :member", Rental.class)
                .setParameter("member",member)
                .getResultList().stream().findFirst();
    }
    public List<Rental> findAllRentalByMember(Member member) {
        return em.createQuery("select r from Rental r where r.member = :member", Rental.class)
                .setParameter("member",member)
                .getResultList();
    }

}
