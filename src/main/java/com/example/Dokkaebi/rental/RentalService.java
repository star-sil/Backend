package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.scooter.DriveLogRepo;
import com.example.Dokkaebi.scooter.ScooterStateRepo;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
import com.example.Dokkaebi.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepo;
    private final MemberService memberService;
    private final TokenService tokenService;
    private final ScooterStateRepo scooterStateRepo;
    private final DriveLogRepo driveLogRepo;

    @Transactional
    public void joinRental(Rental rental) {
        rentalRepo.save(rental);
    }

    public Rental findRental(Member member) {
        return rentalRepo.findOneRentalByMember(member)
                .orElseThrow(() -> new ApiException(ExceptionEnum.RentalNotMatched));
    }

    @Transactional
    public void startRental(Member member, RentalRequestDto rentalRequestDto) {
        ScooterState scooterState = scooterStateRepo.findScootersByStatus(Status.NONE).stream().findFirst()
                .orElseThrow(() -> new ApiException(ExceptionEnum.NotExistAvailableScooter));
        scooterState.changeStatus(Status.WAIT);
        rentalRepo.save(new Rental(member,rentalRequestDto,scooterState));
        log.info("rental: " + member.getIdentity() + " " + scooterState.getIdentity() + " " + scooterState.getStatus());
    }

    public RentalHisResDto findAllRentalByMember(String accessToken) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        List<Rental> rentals = rentalRepo.findAllRentalByMember(member);
        RentalHisResDto rentalHisResDto = new RentalHisResDto();
        for (Rental rental : rentals) {
            List<DriveLog> driveLogs = driveLogRepo.findLogByRental(rental);
            rentalHisResDto.addRentalInfo(rental,driveLogs);

        }
        return rentalHisResDto;
    }

    public RentalResDto findRentalById(Long id) {
        Rental rental = rentalRepo.findById(id);
        List<DriveLog> driveLogs = driveLogRepo.findLogByRental(rental);
        RentalResDto rentalResDto = new RentalResDto();
        for (DriveLog driveLog : driveLogs) {
            rentalResDto.addRideHistory(driveLog);
        }
        return rentalResDto;
    }

    @Transactional
    public void returnScooter(String accessToken) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        Rental rental = rentalRepo.findOneRentalByMember(member)
                .orElseThrow(()->new ApiException(ExceptionEnum.RentalNotMatched));
        ScooterState scooterState = scooterStateRepo.findOneById(rental.getScooterState().getId());
        scooterState.changeStatus(Status.NONE);
    }
}
