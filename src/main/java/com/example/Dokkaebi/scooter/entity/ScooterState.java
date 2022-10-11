package com.example.Dokkaebi.scooter.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
public class ScooterState {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "scooter_id")
    private Scooter scooter;
    private String stat;
    private int soc;
    private double volt;
    private double current;
    private int temp;
    private double lat;
    private double lon;
    private String pow;
    private int shock;
    private LocalDateTime time;
    @ManyToOne @JoinColumn(name = "drive_log_id")
    private DriveLog driveLog;

    @Builder
    public ScooterState(Scooter scooter, String stat, int soc, double volt, double current, int temp,
                        double lat, double lon, String pow, int shock, LocalDateTime time, DriveLog driveLog) {
        this.scooter = scooter;
        this.stat = stat;
        this.soc = soc;
        this.volt = volt;
        this.current = current;
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
        this.pow = pow;
        this.shock = shock;
        this.time = time;
        this.driveLog = driveLog;
    }
}
