package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Scooter;
import com.example.Dokkaebi.domain.Status;
import lombok.Getter;

@Getter
public class ScooterRecordResponseDto {
    private String bike;
    private double lat;
    private double lon;
    private Status status;

    public ScooterRecordResponseDto(Scooter scooter){
        bike = scooter.getBike();
        lat = scooter.getLat();
        lon = scooter.getLon();
        status = scooter.getStatus();
    }
}
