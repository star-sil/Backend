package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.scooter.entity.DriveLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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
}
