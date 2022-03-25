package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.controller.dtos.RentalRequestDto;
import com.example.Dokkaebi.domain.Rental;
import com.example.Dokkaebi.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void createRental(@RequestBody RentalRequestDto rentalRequestDto){
        rentalRequestDto.calculatePrice();
        Rental rental = rentalRequestDto.toEntity();
        rentalService.joinRental(rental);
    }
}
