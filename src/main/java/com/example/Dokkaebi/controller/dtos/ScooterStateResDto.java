package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.ScooterState;
import com.example.Dokkaebi.domain.Status;
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
