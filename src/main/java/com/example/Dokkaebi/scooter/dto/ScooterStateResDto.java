package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScooterStateResDto {
    private Long id;
    private String stat;
    private int soc;
    private double volt;
    private String current;
    private int temp;
    private int speed;
    private double lat;
    private double lng;
    private String pow;
    private int shock;
    private LocalDateTime time;

    public ScooterStateResDto(ScooterState scooterState) {
        this.id = scooterState.getId();
        this.stat = scooterState.getStat();
        this.soc = scooterState.getSoc();
        this.volt = scooterState.getVolt();
        this.current = scooterState.getCurrent();
        this.temp = scooterState.getTemp();
        this.speed = scooterState.getSpeed();
        this.lat = scooterState.getLat();
        this.lng = scooterState.getLng();
        this.pow = scooterState.getPow();
        this.shock = scooterState.getShock();
        this.time = scooterState.getTime();
    }
}
