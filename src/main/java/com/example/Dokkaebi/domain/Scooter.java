package com.example.Dokkaebi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@Table(name = "test")
@NoArgsConstructor
public class Scooter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bike;
    private String stat;
    private String soc;
    private String volt;
    private String temp;
    private String lat;
    private String lon;
    private String pow;
    private LocalDateTime time;

    @Builder
    public Scooter(String bike, String stat, String soc, String volt, String temp, String lat, String lon, String pow, String shoc, LocalDateTime time) {
        this.bike = bike;
        this.stat = stat;
        this.soc = soc;
        this.volt = volt;
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
        this.pow = pow;
        this.time = time;
    }

}
