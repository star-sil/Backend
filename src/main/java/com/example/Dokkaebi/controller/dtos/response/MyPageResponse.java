package com.example.Dokkaebi.controller.dtos.response;

import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Rental;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

// 표출할 정보가 더 생기는대로 추가하여 반환하면 될 듯.
@Getter
public class MyPageResponse {
    private List<Rental> rentals;
    private Member member;

    @Builder
    public MyPageResponse(List<Rental> rental, Member member){
        this.rentals = rental;
        this.member = member;
    }
}
