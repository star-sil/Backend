package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.Scooter;

import java.time.LocalDateTime;

public class ScooterLocationRes {
    private double lat;
    private double lng;
    private int soc;
    private LocalDateTime endDate;

    public ScooterLocationRes(Scooter scooter) {
        this.lat = scooter.getLat();
        this.lng = scooter.getLon();
        this.soc = scooter.getSoc();
        this.endDate = scooter.getTime();
    }
}
