package com.example.Dokkaebi.rental.dto;

import com.example.Dokkaebi.rental.Rental;
import com.example.Dokkaebi.scooter.entity.DriveLog;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class RentalHisResDto {
    List<RentalInfo> rentalHistory = new ArrayList<>();

    public void addRentalInfo(Rental rental, List<DriveLog> driveLogs) {
        rentalHistory.add(new RentalInfo(rental, driveLogs));
    }

}

@Data
class RentalInfo {
    private Long rentalId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double distance;
    private int rideCount;
    private Long cost;

    public RentalInfo(Rental rental, List<DriveLog> driveLogs) {
        this.rentalId = rental.getId();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        sumAllDriveDist(driveLogs);
        this.cost = rental.getPrice();
    }

    private void sumAllDriveDist(List<DriveLog> driveLogs) {
        for (DriveLog driveLog : driveLogs) {
            this.distance += driveLog.calculateDist();
        }
    }
}
