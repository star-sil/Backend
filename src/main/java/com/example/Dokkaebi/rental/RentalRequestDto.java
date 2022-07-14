package com.example.Dokkaebi.rental;

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
    private Long price;

    public long calculatePrice(){
        this.price = ChronoUnit.DAYS.between(this.startDate,this.endDate) * 5000L;
        return this.price;
    }

}
