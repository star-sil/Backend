package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.Scooter;
import lombok.Getter;

@Getter
public class ScooterRecordResponseDto {
    private double lat;
    private double lon;

    public ScooterRecordResponseDto(Scooter scooter){
        lat = scooter.getLat();
        lon = scooter.getLon();
    }
}
