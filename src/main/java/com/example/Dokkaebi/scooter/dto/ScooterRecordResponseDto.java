package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.ScooterState;
import lombok.Getter;

@Getter
public class ScooterRecordResponseDto {
    private double lat;
    private double lon;

    public ScooterRecordResponseDto(ScooterState scooterState){
        lat = scooterState.getLat();
        lon = scooterState.getLon();
    }
}
