package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.rental.dto.RentalHisResDto;
import com.example.Dokkaebi.rental.dto.RentalRequestDto;
import com.example.Dokkaebi.rental.dto.RentalResDto;
import com.example.Dokkaebi.rental.dto.RentalStatResDto;
import com.example.Dokkaebi.scooter.Repo.DriveLogRepo;
import com.example.Dokkaebi.scooter.Repo.ScooterRepo;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.scooter.entity.Status;
import com.example.Dokkaebi.token.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepo;
    private final MemberService memberService;
    private final TokenService tokenService;
    private final ScooterRepo scooterRepo;
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
        Scooter scooter = scooterRepo.findScootersByStatus(Status.NONE).stream().findFirst()
                .orElseThrow(() -> new ApiException(ExceptionEnum.NotExistAvailableScooter));
        scooter.changeStatus(Status.WAIT);
        rentalRepo.save(new Rental(member,rentalRequestDto, scooter));
        log.info("rental: " + member.getIdentity() + " " + scooter.getIdentity() + " " + scooter.getStatus());
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
        Scooter scooter = scooterRepo.findOneById(rental.getScooter().getId());
        scooter.changeStatus(Status.NONE);
    }

    public List<RentalStatResDto> findAllRentalByStatus(Status status) {
        List<RentalStatResDto> rentalStatResDtos = new ArrayList<>();
        List<Scooter> scooters = scooterRepo.findScootersByStatus(status);
        for (Scooter scooter : scooters) {
            Optional<Rental> rental = scooter.getRentals().stream().findFirst();
            if (rental.isPresent()) {
                rentalStatResDtos.add(new RentalStatResDto(rental.get(), scooter.getIdentity()));
            } else {
                rentalStatResDtos.add(new RentalStatResDto(scooter.getIdentity()));
            }
        }
        return rentalStatResDtos;
    }

    @Transactional
    public void processRentalReq(Long rentalId) {
        Rental rental = rentalRepo.findById(rentalId);
        rental.getScooter().changeStatus(Status.RENTAL);
    }
}
