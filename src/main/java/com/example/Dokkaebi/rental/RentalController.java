package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.member.Member;
import com.example.Dokkaebi.member.MemberService;
import com.example.Dokkaebi.rental.dto.RentalHisResDto;
import com.example.Dokkaebi.rental.dto.RentalRequestDto;
import com.example.Dokkaebi.rental.dto.RentalResDto;
import com.example.Dokkaebi.rental.dto.RentalStatResDto;
import com.example.Dokkaebi.scooter.entity.Status;
import com.example.Dokkaebi.token.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final MemberService memberService;
    private final TokenService tokenService;

    @PostMapping("/rental/price")
    public long rentalPrice(@RequestBody RentalRequestDto rentalRequestDto) {
        return rentalRequestDto.calculatePrice();
    }

    @PostMapping("/rental/new")
    public void startRental(@RequestHeader(value = "Authorization") @NotNull String accessToken, @RequestBody RentalRequestDto rentalRequestDto) {
        String token = tokenService.getIdentityFromToken(accessToken);
        Member member = memberService.findMember(token);
        rentalService.startRental(member, rentalRequestDto);
    }

    @ApiOperation(value = "킥보드 렌탈기록")
    @GetMapping("/rental")
    public RentalHisResDto checkRental(@RequestHeader(value = "Authorization") String accessToken) {
        return rentalService.findAllRentalByMember(accessToken);
    }

    @ApiOperation(value = "킥보드 주행기록")
    @GetMapping("/rental/{id}")
    public RentalResDto checkRideHistory(@PathVariable Long id) {
        return rentalService.findRentalById(id);
    }

    @ApiOperation(value = "대여한 킥보드 반납")
    @PostMapping("/rental/return")
    public void returnScooter(@RequestHeader(value = "Authorization") String accessToken) {
        rentalService.returnScooter(accessToken);
    }

    @ApiOperation(value = "대여 요청 정보보기")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("rental/admin")
    public ResponseEntity<List<RentalStatResDto>> checkRentalReq(@RequestParam(value = "status") Status status) {
        return ResponseEntity.ok(rentalService.findAllRentalByStatus(status));
    }

    @ApiOperation(value = "대여 요청 처리")
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("rental/{rentalId}")
    public void processRentalReq(@RequestParam(value = "rentalId") Long rentalId) {
        rentalService.processRentalReq(rentalId);
    }
}
