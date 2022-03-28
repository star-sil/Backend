package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Scooter;
import com.example.Dokkaebi.domain.Status;
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
