package com.example.Dokkaebi.rental.dto;

import com.example.Dokkaebi.rental.Rental;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentalStatResDto {
    private Long rentalId;
    private String address;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate date;
    private String identity;

    public RentalStatResDto(Rental rental) {
        this.rentalId = rental.getId();
        this.address = rental.getAddress();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.date = rental.getDate();
        this.identity = rental.getMember().getIdentity();
    }
}
