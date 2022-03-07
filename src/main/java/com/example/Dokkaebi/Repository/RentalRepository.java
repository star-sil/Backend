package com.example.Dokkaebi.Repository;

import com.example.Dokkaebi.domain.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class RentalRepository {
    private final EntityManager em;

    public void save(Rental rental){
        em.persist(rental);
    }

}
