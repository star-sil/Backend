package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.scooter.entity.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScooterRepository {
    private final EntityManager em;

    public List<Scooter> findByBike(String bike){
        return em.createQuery("select s from Scooter s where s.bike = :bike order by s.time desc", Scooter.class)
                .setParameter("bike", bike)
                .setMaxResults(1)
                .getResultList();
    }

    public List<Scooter> findAllByBike(List<String> bikes) {
        List<Scooter> results = new ArrayList<>();
        for(String bike : bikes){
            results.add(findByBike(bike).get(0));
        }
        return results;
    }

    public void save(Scooter scooter) {
        em.persist(scooter);
    }

    public List<Scooter> findBikeByIdentity(String identity) {
        return em.createQuery("select s from Scooter s where s.identity = :identity", Scooter.class)
                .setParameter("identity",identity)
                .getResultList();
    }
}
