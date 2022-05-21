package com.example.Dokkaebi.controller.dtos.response;

import com.example.Dokkaebi.domain.Rental;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MyPageResponse {
    private List<Rental> rentals;

    @Builder
    public MyPageResponse(List<Rental> rental){
        this.rentals = rental;
    }
}
