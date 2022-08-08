package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScooterRentalStateResDto {
    private Status status;
    private LocalDate startDate;
    private LocalDate endDate;

    public ScooterRentalStateResDto(Rental rental) {
        this.status = rental.getScooterState().getStatus();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
    }

    public ScooterRentalStateResDto(Status status) {
        this.status = status;
    }
}
