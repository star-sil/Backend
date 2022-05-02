package com.example.Dokkaebi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class DriveLog {
    @Id @GeneratedValue
    private Long id;
    private String identity;
    private int cycle;
    @OneToMany(mappedBy = "driveLog")
    private List<Scooter> scooters = new ArrayList<>();

    @Builder
    public DriveLog(Scooter scooter, int cycle) {
        this.identity = scooter.getIdentity();
        this.cycle = cycle;
    }

    public void startDrive(Scooter scooter) {
        this.cycle += 1;
        scooters.add(scooter);
    }

    public void addLog(Scooter scooter) {
        this.scooters.add(scooter);
    }
}
