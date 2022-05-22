package com.example.Dokkaebi.scooter.entity;

import com.example.Dokkaebi.rental.Rental;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class DriveLog {
    @Id @GeneratedValue
    private Long id;
    private int useCount;
    private LocalDateTime startTime;
    private String scooterIdentity;

    @OneToMany(mappedBy = "driveLog")
    private List<Scooter> scooters = new ArrayList<>();

    @ManyToOne @JoinColumn(name = "rental_id")
    private Rental rental;

    @Builder
    public DriveLog(int useCount, Scooter scooter, LocalDateTime startTime,Rental rental) {
        this.useCount = useCount;
        this.scooters.add(scooter);
        this.rental = rental;
        this.scooterIdentity = scooter.getIdentity();
        this.startTime = startTime;
    }

    public double calculateDist() {
        double distance = 0;
        if(scooters.size() <= 1){
            return distance;
        } else{
            for(int i = 1; i < scooters.size(); ++i){
                distance += calDistance(scooters.get(i-1).getLat(),scooters.get(i-1).getLon(),
                        scooters.get(i).getLat(),scooters.get(i).getLon());
            }
        }
        return distance;
    }

    private double calDistance(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
