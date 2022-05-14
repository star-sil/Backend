package com.example.Dokkaebi.controller.dtos;


import com.example.Dokkaebi.domain.ScooterState;
import com.example.Dokkaebi.domain.Status;
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
                        .cycle(0)
                        .build();
    }
}
