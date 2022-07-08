package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepo;
    private final MemberService memberService;

    @Transactional
    public void joinRental(Rental rental) {
        rentalRepo.save(rental);
    }

    public Rental findRental(Member member) {
        return rentalRepo.findRentalByMember(member).orElseThrow(() -> new ApiException(ExceptionEnum.RentalNotMatched));
    }
}
