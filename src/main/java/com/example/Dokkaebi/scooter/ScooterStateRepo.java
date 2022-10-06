package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScooterStateRepo {
    private final EntityManager em;

    public List<ScooterState> findAll() {
        return em.createQuery("select s from ScooterState s",ScooterState.class)
                .getResultList();
    }

    public Optional<ScooterState> findOne(String identity) {
        return em.createQuery("select s from ScooterState s where s.identity = :identity",ScooterState.class)
                .setParameter("identity", identity).getResultList().stream().findFirst();
    }

    public ScooterState findOneById(Long id) {
        return em.find(ScooterState.class,id);
    }

    public void save(ScooterState scooterState) {
        em.persist(scooterState);
    }

    public List<ScooterState> findScootersByStatus(Status status) {
        return em.createQuery("select s from ScooterState s where s.status = :status order by s.id DESC")
                .setParameter("status",status)
                .getResultList();
    }
}
