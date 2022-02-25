package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Scooter;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScooterResponseDto {
    private String bike;
    private String lat;
    private String lon;

    @Builder
    public ScooterResponseDto(String bike, String lat, String lon) {
        this.bike = bike;
        this.lat = lat;
        this.lon = lon;
        changeLocationFormat();
    }

    private void changeLocationFormat() {
        this.lat = this.lat.substring(0,3) + "." + this.lat.substring(3,8);
        this.lon = this.lon.substring(0,3) + "." + this.lon.substring(3,8);
    }


}
