package com.example.Dokkaebi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
public class Scooter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identity;
    private String stat;
    private int soc;
    private double volt;
    private int temp;
    private double lat;
    private double lon;
    private String pow;
    private int shock;
    private LocalDateTime time;
    @ManyToOne @JoinColumn(name = "driveLog_id")
    private DriveLog driveLog;

    @Builder
    public Scooter(String identity, String stat, int soc, double volt, int temp, double lat, double lon, String pow, int shock, LocalDateTime time, DriveLog driveLog) {
        this.identity = identity;
        this.stat = stat;
        this.soc = soc;
        this.volt = volt;
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
        this.pow = pow;
        this.shock = shock;
        this.time = time;
        this.driveLog = driveLog;
    }
}
