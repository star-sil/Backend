package com.example.Dokkaebi.rental.dto;

import com.example.Dokkaebi.scooter.entity.DriveLog;
import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RentalResDto {
    private List<RideHistory> rideHistory = new ArrayList<>();

    public void addRideHistory(DriveLog driveLog) {
        this.rideHistory.add(new RideHistory(driveLog));
    }
}

@Data
class RideHistory {
    private double distance;
    private Long rideTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Gps> route = new ArrayList<>();

    public RideHistory(DriveLog driveLog) {
        this.distance = driveLog.calculateDist();
        this.startDate = driveLog.getScooterStates().get(0).getTime();
        this.endDate = driveLog.getScooterStates().get(driveLog.getScooterStates().size()-1).getTime();
        this.rideTime = calRideTime(this.startDate,this.endDate);
        sumRide(driveLog);
    }

    public long calRideTime(LocalDateTime startDate, LocalDateTime endDate) {
        return Duration.between(startDate, endDate).getSeconds();
    }

    public void sumRide(DriveLog driveLog) {
        for (ScooterState scooterState : driveLog.getScooterStates()) {
            this.route.add(new Gps(scooterState.getLat(), scooterState.getLon()));
        }
    }
}

@Data
@AllArgsConstructor
class Gps {
    private double lat;
    private double lng;
}

