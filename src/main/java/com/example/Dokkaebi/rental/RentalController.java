package com.example.Dokkaebi.rental;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
        rentalService.startRental(accessToken,rentalRequestDto);
    }
}
