package com.example.Dokkaebi.domain;

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
    private int price;
    @OneToOne(mappedBy = "rental")
    private Scooter scooter;

    @Builder
    public Rental(Member member, LocalDate startDate, LocalDate endDate, int price, Scooter scooter) {
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.scooter = scooter;
    }
}
