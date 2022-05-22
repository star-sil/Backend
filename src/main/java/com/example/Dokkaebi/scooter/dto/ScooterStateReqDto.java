package com.example.Dokkaebi.scooter.dto;


import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScooterStateReqDto {
    private String identity;

    public ScooterState toEntity() {
        return ScooterState.builder()
                        .identity(identity)
                        .status(Status.NONE)
                        .useCount(0)
                        .build();
    }
}
