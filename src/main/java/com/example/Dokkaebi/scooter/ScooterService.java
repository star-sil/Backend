package com.example.Dokkaebi.scooter;



import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.rental.RentalRepository;
import com.example.Dokkaebi.rental.RentalService;
import com.example.Dokkaebi.scooter.Repo.DriveLogRepo;
import com.example.Dokkaebi.scooter.Repo.ScooterRepo;
import com.example.Dokkaebi.scooter.Repo.ScooterStateRepository;
import com.example.Dokkaebi.scooter.dto.*;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.Status;
import com.example.Dokkaebi.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterStateRepository scooterStateRepo;
    private final ScooterRepo scooterRepo;
    private final DriveLogRepo driveLogRepo;
    private final TokenService tokenService;
    private final MemberService memberService;
    private final RentalService rentalService;
    private final RentalRepository rentalRepo;


    @Transactional
    public void enroll(ScooterStateReqDto scooterStateReqDto) throws Exception {
        if(scooterRepo.findOne(scooterStateReqDto.getIdentity()).isEmpty()){
            scooterRepo.save(scooterStateReqDto.toEntity());
        } else{
            throw new Exception("이미 해당 스쿠터가 등록되어 있습니다.");
        }
    }

    public DriveLogDto checkDriveLog(String identity, int useCount) {
        DriveLog driveLog = driveLogRepo.findLogByIdentityAndUseCount(identity, useCount).get(0);
        return new DriveLogDto(driveLog);
    }

    public ScooterLocationRes findScooterByMember(String accessToken) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        Rental rental = rentalService.findRental(member);
        DriveLog driveLog = driveLogRepo.findLogByRental(rental).get(0);
        return new ScooterLocationRes(driveLog.getScooterStates().get(0));
    }

    public ScooterRentalStateResDto checkScooterState(String accessToken) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        Optional<Rental> rental = rentalRepo.findAllRentalByMember(member).stream().findFirst();
        if(rental.isPresent()) {
            ScooterState scooter = scooterStateRepo.findByScooter(rental.get().getScooter()).get(0);
            return new ScooterRentalStateResDto(rental.get(),scooter);
        }
        else return new ScooterRentalStateResDto(Status.NONE);
    }
}
