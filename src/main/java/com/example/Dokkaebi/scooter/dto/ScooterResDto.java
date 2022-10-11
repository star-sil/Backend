package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScooterResDto {
    private String identity;
    private Status status;

    @Builder
    public ScooterResDto(Scooter scooter) {
        this.identity = scooter.getIdentity();
        this.status = scooter.getStatus();
    }
}
