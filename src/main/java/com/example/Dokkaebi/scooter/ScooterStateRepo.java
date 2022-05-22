package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScooterStateRepo {
    private final EntityManager em;

    public List<ScooterState> findAll() {
        return em.createQuery("select s from ScooterState s",ScooterState.class)
                .getResultList();
    }

    public List<ScooterState> findOne(String identity) {
        return em.createQuery("select s from ScooterState s where s.identity = :identity",ScooterState.class)
                .setParameter("identity", identity)
                .getResultList();
    }

    public void save(ScooterState scooterState) {
        em.persist(scooterState);
    }
}
