package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScooterRentalStateResDto {
    private Long rentalId;
    private String bikeNum;
    private Status status;
    private LocalDate startDate;
    private LocalDate endDate;
    private double lat;
    private double lng;
    private int soc;

    public ScooterRentalStateResDto(Rental rental, ScooterState scooterState) {
        this.rentalId = rental.getId();
        this.status = rental.getScooter().getStatus();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.bikeNum = rental.getScooter().getIdentity();
        this.lat = scooterState.getLat();
        this.lng = scooterState.getLng();
        this.soc = scooterState.getSoc();
    }

    public ScooterRentalStateResDto(Status status) {
        this.status = status;
    }
}
