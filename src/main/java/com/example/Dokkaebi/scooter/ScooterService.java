package com.example.Dokkaebi.scooter;

import com.example.Dokkaebi.scooter.dto.DriveLogDto;
import com.example.Dokkaebi.scooter.dto.ScooterStateReqDto;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository scooterRepo;
    private final ScooterStateRepo scooterStateRepo;
    private final DriveLogRepo driveLogRepo;

    public List<ScooterState> findAll() {
        return scooterStateRepo.findAll();
    }

    @Transactional
    public void enroll(ScooterStateReqDto scooterStateReqDto) throws Exception {
        if(scooterStateRepo.findOne(scooterStateReqDto.getIdentity()).isEmpty()){
            scooterStateRepo.save(scooterStateReqDto.toEntity());
        } else{
            throw new Exception("이미 해당 스쿠터가 등록되어 있습니다.");
        }
    }

    public DriveLogDto checkDriveLog(String identity, int useCount) {
        DriveLog driveLog = driveLogRepo.findLogByIdentityAndUseCount(identity, useCount).get(0);
        return new DriveLogDto(driveLog.getStartTime(),driveLog.calculateDist(),
                driveLog.getScooters().get(driveLog.getScooters().size()-1).getLat(),
                driveLog.getScooters().get(driveLog.getScooters().size()-1).getLon());
    }

    public List<Scooter> findScooter(Scooter scooter) {
        return scooterRepo.findByBike(scooter.getIdentity());
    }

    public List<Scooter> findAllScooter() {
        List<String> bikes = new ArrayList<>();
        bikes.add("0001"); bikes.add("0002"); bikes.add("0003"); bikes.add("0004");
        bikes.add("0005"); bikes.add("0006");
        return scooterRepo.findAllByBike(bikes);
    }

    public List<Scooter> findScooterByIdentity(String identity){
        return scooterRepo.findBikeByIdentity(identity);
    }
}
