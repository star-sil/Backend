package com.example.Dokkaebi.scooter.Repo;

import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScooterStateRepository {
    private final EntityManager em;

    public List<ScooterState> findByScooter(Scooter scooter){
        return em.createQuery("select s from ScooterState s where s.scooter = :scooter order by s.time desc", ScooterState.class)
                .setParameter("scooter", scooter)
                .setMaxResults(1)
                .getResultList();
    }

    public void save(ScooterState scooterState) {
        em.persist(scooterState);
    }

    public List<ScooterState> findAll() {
        return em.createQuery("select s from ScooterState s", ScooterState.class)
                .getResultList();
    }
}
