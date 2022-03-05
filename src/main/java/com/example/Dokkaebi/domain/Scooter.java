package com.example.Dokkaebi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private int soc;
    private double volt;
    private int temp;
    private double lat;
    private double lon;
    private String pow;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int shock;
    private LocalDateTime time;

    @Builder
    public Scooter(String bike, String stat, int soc, double volt, int temp, double lat, double lon, String pow, Status status, int shock, LocalDateTime time) {
        this.bike = bike;
        this.stat = stat;
        this.soc = soc;
        this.volt = volt;
        this.temp = temp;
        this.lat = lat;
        this.lon = lon;
        this.pow = pow;
        this.status = status;
        this.shock = shock;
        this.time = time;
    }
}
