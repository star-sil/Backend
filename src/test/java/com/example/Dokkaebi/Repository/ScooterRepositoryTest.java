package com.example.Dokkaebi.Repository;

import com.example.Dokkaebi.domain.Scooter;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ScooterRepositoryTest {
    
    @Autowired
    private ScooterRepository scooterRepository;

    @Test
    public void 스쿠터번호로_찾기() throws Exception{
        //given
        LocalDateTime date = LocalDateTime.now().MIN;
        Scooter scooter = new Scooter("bike","","","","","","","","",date);

        //when
        scooterRepository.save(scooter);
        List<Scooter> scooters = scooterRepository.findByBike(scooter.getBike());
        //then
        Assertions.assertThat(scooters.get(0)).isEqualTo(scooter);
    }

    @Test
    public void 모든스쿠터최신으로조회() throws Exception{
        //given
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime lateDate = LocalDateTime.now().plusDays(1);
        Scooter scooter1 = new Scooter("0001","","","","","","","","",date);
        Scooter scooter2 = new Scooter("0001","","","","","","","","", lateDate);
        Scooter scooter3 = new Scooter("0002","","","","","","","","",date);
        List<Scooter> scooters = new ArrayList<>();
        List<String> bikes = new ArrayList<>();
        scooters.add(scooter1); scooters.add(scooter2); scooters.add(scooter3);
        bikes.add(scooter1.getBike()); bikes.add(scooter2.getBike()); bikes.add(scooter3.getBike());

        //when
        for(Scooter scooter : scooters) {
            scooterRepository.save(scooter);
        }
        scooters.remove(1);
        List<Scooter> allByBike = scooterRepository.findAllByBike(bikes);

        //then
        Assertions.assertThat(allByBike.get(0).getTime()).isEqualTo(scooter2.getTime());
        Assertions.assertThat(allByBike.get(2).getBike()).isEqualTo(scooter3.getBike());
    }
}