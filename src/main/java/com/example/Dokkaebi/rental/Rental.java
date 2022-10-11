package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.rental.dto.RentalRequestDto;
import com.example.Dokkaebi.scooter.entity.Scooter;
import com.example.Dokkaebi.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Rental {
    @Id @GeneratedValue
    private Long id;
    @ManyToOne @JoinColumn(name = "member_id")
    private Member member;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime requestTime;
    private Long price;
    private String address;
    @ManyToOne @JoinColumn(name = "scooter_id")
    private Scooter scooter;

    @Builder
    public Rental(Member member, RentalRequestDto rentalRequestDto, Scooter scooter) {
        this.member = member;
        this.startDate = rentalRequestDto.getStartDate();
        this.endDate = rentalRequestDto.getEndDate();
        this.price = rentalRequestDto.getPrice();
        this.address = rentalRequestDto.getAddress();
        this.scooter = scooter;
        this.scooter.addRental(this);
        this.requestTime = LocalDateTime.now();
    }
}
