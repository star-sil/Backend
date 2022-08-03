package com.example.Dokkaebi.rental.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Slf4j
@Getter
@NoArgsConstructor
public class RentalRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate date;
    private Long price;
    private String address;

    public long calculatePrice(){
        this.price = ChronoUnit.DAYS.between(this.startDate,this.endDate) * 5000L;
        return this.price;
    }

}
