package com.example.Dokkaebi.rental;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/rental/price")
    public long rentalPrice(@RequestBody RentalRequestDto rentalRequestDto) {
        return rentalRequestDto.calculatePrice();
    }

    @PostMapping("/rental/new")
    public void startRental(@RequestHeader(value = "access_token") String accessToken, @RequestBody RentalRequestDto rentalRequestDto) {
        rentalService.startRental(accessToken, rentalRequestDto);
    }

    @GetMapping("/rental")
    public RentalHisResDto checkRental(@RequestHeader(value = "access_token") String accessToken) {
        return rentalService.findAllRentalByMember(accessToken);
    }
}
