package com.example.Dokkaebi.controller.dtos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DriveLogDto {
    private LocalDateTime startTime;
    private double driveDist;
    private double lat;
    private double lon;

    public DriveLogDto(LocalDateTime startTime, double driveDist, double lat, double lon) {
        this.startTime = startTime;
        this.driveDist = driveDist;
        this.lat = lat;
        this.lon = lon;
    }
}
