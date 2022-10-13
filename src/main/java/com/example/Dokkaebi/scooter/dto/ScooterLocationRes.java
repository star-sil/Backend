package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScooterLocationRes {
    private double lat;
    private double lng;
    private int soc;
    private LocalDateTime endDate;

    public ScooterLocationRes(ScooterState scooterState) {
        this.lat = scooterState.getLat();
        this.lng = scooterState.getLng();
        this.soc = scooterState.getSoc();
        this.endDate = scooterState.getTime();
    }
}
