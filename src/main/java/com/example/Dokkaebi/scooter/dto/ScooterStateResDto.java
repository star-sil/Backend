package com.example.Dokkaebi.scooter.dto;

import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScooterStateResDto {
    private String identity;
    private Status status;
    private int useCount;

    @Builder
    public ScooterStateResDto(ScooterState scooterState) {
        this.identity = scooterState.getIdentity();
        this.status = scooterState.getStatus();
        this.useCount = scooterState.getUseCount();
    }
}
