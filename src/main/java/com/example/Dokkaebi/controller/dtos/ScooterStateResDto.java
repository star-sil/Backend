package com.example.Dokkaebi.controller.dtos;

import com.example.Dokkaebi.domain.Scooter;
import com.example.Dokkaebi.domain.ScooterState;
import com.example.Dokkaebi.domain.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScooterStateResDto {
    private String identity;
    private Status status;
    private int cycle;

    @Builder
    public ScooterStateResDto(ScooterState scooterState) {
        this.identity = scooterState.getIdentity();
        this.status = scooterState.getStatus();
        this.cycle = scooterState.getCycle();
    }
}
