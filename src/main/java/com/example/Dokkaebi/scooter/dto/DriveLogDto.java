package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.domain.DriveLog;
import com.example.Dokkaebi.domain.Scooter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DriveLogDto {
    private LocalDateTime startTime;
    private double driveDist;
    private int soc;
    private List<Route> route = new ArrayList<>();

    public DriveLogDto(DriveLog driveLog) {
        this.startTime = driveLog.getStartTime();
        this.driveDist = driveLog.calculateDist();

        for (Scooter scooter : driveLog.getScooters()) {
            this.soc = scooter.getSoc();
            route.add(new Route(scooter.getLat(),scooter.getLon()));
        }
    }

    @Data
    @AllArgsConstructor
    private class Route {
        double lat;
        double lon;
    }
}


