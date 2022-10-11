package com.example.Dokkaebi.scooter.Repo;

import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScooterRepo {
    private final EntityManager em;

    public List<Scooter> findAll() {
        return em.createQuery("select s from Scooter s", Scooter.class)
                .getResultList();
    }

    public Optional<Scooter> findOne(String identity) {
        return em.createQuery("select s from Scooter s where s.identity = :identity", Scooter.class)
                .setParameter("identity", identity).getResultList().stream().findFirst();
    }

    public Scooter findOneById(Long id) {
        return em.find(Scooter.class,id);
    }

    public void save(Scooter scooter) {
        em.persist(scooter);
    }

    public List<Scooter> findScootersByStatus(Status status) {
        return em.createQuery("select s from Scooter s where s.status = :status order by s.id DESC")
                .setParameter("status",status)
                .getResultList();
    }
}
