package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Scooter;
import com.example.Dokkaebi.domain.Status;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScooterResponseDto {
    private String bike;
    private int soc;
    private double lat;
    private double lon;
    private Status status;

    @Builder
    public ScooterResponseDto(Scooter scooter) {
        this.bike = scooter.getBike();
        this.soc = scooter.getSoc();
        this.lat = scooter.getLat();
        this.lon = scooter.getLon();
        this.status = scooter.getStatus();
    }


}
