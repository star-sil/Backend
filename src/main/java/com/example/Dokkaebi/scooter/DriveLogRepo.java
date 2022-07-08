package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DriveLogRepo {
    private final EntityManager em;

    public List<DriveLog> findLogByIdentityAndUseCount(String identity, int useCount) {
        return em.createQuery("select d from DriveLog d where d.scooterIdentity = :identity and " +
                "d.useCount = :useCount",DriveLog.class)
                .setParameter("identity",identity)
                .setParameter("useCount",useCount)
                .getResultList();
    }

    public Optional<DriveLog> findLogByRental(Rental rental) {
        return em.createQuery("select d from DriveLog d where d.rental = :rental",DriveLog.class)
                .setParameter("rental",rental)
                .getResultList().stream().findFirst();
    }
}
