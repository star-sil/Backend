package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.ScooterState;
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

        for (ScooterState scooterState : driveLog.getScooterStates()) {
            this.soc = scooterState.getSoc();
            route.add(new Route(scooterState.getLat(), scooterState.getLng()));
        }
    }

    @Data
    @AllArgsConstructor
    private class Route {
        double lat;
        double lng;
    }
}


