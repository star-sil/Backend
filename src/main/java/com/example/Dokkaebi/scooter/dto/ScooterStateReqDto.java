package com.example.Dokkaebi.scooter.dto;


import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.scooter.entity.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScooterStateReqDto {
    private String identity;

    public Scooter toEntity() {
        return Scooter.builder()
                        .identity(identity)
                        .status(Status.NONE)
                        .build();
    }
}
