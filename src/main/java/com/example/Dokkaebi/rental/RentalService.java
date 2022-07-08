package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.scooter.ScooterService;
import com.example.Dokkaebi.scooter.ScooterStateRepo;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
import com.example.Dokkaebi.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepo;
    private final MemberService memberService;
    private final TokenService tokenService;
    private final ScooterStateRepo scooterStateRepo;

    @Transactional
    public void joinRental(Rental rental) {
        rentalRepo.save(rental);
    }

    public Rental findRental(Member member) {
        return rentalRepo.findRentalByMember(member)
                .orElseThrow(() -> new ApiException(ExceptionEnum.RentalNotMatched));
    }

    @Transactional
    public void startRental(String accessToken, RentalRequestDto rentalRequestDto) {
        String identity = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(identity);
        ScooterState scooterState = scooterStateRepo.findScootersByStatus(Status.NONE).stream().findFirst()
                .orElseThrow(() -> new ApiException(ExceptionEnum.NotExistAvailableScooter));
        scooterState.changeStatus(Status.RENTAL);
        rentalRepo.save(new Rental(member,rentalRequestDto,scooterState));
    }
}
