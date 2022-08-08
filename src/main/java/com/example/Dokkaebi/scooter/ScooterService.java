package com.example.Dokkaebi.scooter;



import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.rental.RentalRepository;
import com.example.Dokkaebi.rental.RentalService;
import com.example.Dokkaebi.scooter.dto.*;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
import com.example.Dokkaebi.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository scooterRepo;
    private final ScooterStateRepo scooterStateRepo;
    private final DriveLogRepo driveLogRepo;
    private final TokenService tokenService;
    private final MemberService memberService;
    private final RentalService rentalService;
    private final RentalRepository rentalRepo;

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
        return new DriveLogDto(driveLog);
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

    public ScooterLocationRes findScooterByMember(String accessToken) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        Rental rental = rentalService.findRental(member);
        DriveLog driveLog = driveLogRepo.findLogByRental(rental).get(0);
        return new ScooterLocationRes(driveLog.getScooters().get(0));
    }

    public ScooterRentalStateResDto checkScooterState(String accessToken) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        Optional<Rental> rental = rentalRepo.findAllRentalByMember(member).stream().findFirst();
        if(rental.isPresent()) return new ScooterRentalStateResDto(rental.get());
        else return new ScooterRentalStateResDto(Status.NONE);
    }
}
