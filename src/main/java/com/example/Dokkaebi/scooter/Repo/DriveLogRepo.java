package com.example.Dokkaebi.scooter.Repo;

import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.Scooter;
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

    public List<DriveLog> findLogByRental(Rental rental) {
        return em.createQuery("select d from DriveLog d where d.rental = :rental",DriveLog.class)
                .setParameter("rental",rental)
                .getResultList();
    }

    // 주행 횟수가 20억을 넘기기 거의 불가능이기 때문에 강제 형변환 해줘도 오버플로우가 나타나지 않을 것이라 생각한다.
    public Integer countDriveLogByScooter(Scooter scooter) {
        return em.createQuery("select count(*) from DriveLog d " +
                        "where d.scooter = :scooter", Long.class)
                .setParameter("scooter", scooter)
                .getSingleResult().intValue();
    }

    public void save(DriveLog driveLog) {
        em.persist(driveLog);
    }
}
