package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.scooter.entity.ScooterState;
import com.example.Dokkaebi.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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
    private Long price;
    @ManyToOne @JoinColumn(name = "scooter_state_id")
    private ScooterState scooterState;

    @Builder
    public Rental(Member member, RentalRequestDto rentalRequestDto, ScooterState scooterState) {
        this.member = member;
        this.startDate = rentalRequestDto.getStartDate();
        this.endDate = rentalRequestDto.getEndDate();
        this.price = rentalRequestDto.getPrice();
        this.scooterState = scooterState;
    }
}
