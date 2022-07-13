package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScooterRentalStateResDto {
    private Status status;
    private LocalDate startDate;
    private LocalDate endDate;
}
