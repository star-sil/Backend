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
    public ScooterResponseDto(String bike, int soc, double lat, double lon, Status status) {
        this.bike = bike;
        this.soc = soc;
        this.lat = lat;
        this.lon = lon;
        this.status = status;
    }


}
