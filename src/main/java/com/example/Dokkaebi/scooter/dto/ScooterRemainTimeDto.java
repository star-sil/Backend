package com.example.Dokkaebi.scooter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScooterRemainTimeDto {
    private int remainTime;

    public ScooterRemainTimeDto(int remainTime) {
        this.remainTime = remainTime;
    }

}
