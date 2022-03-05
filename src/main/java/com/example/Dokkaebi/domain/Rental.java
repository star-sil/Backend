package com.example.Dokkaebi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Rental {

    @Id @GeneratedValue
    private Long id;
    private String identity;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long price;

    @Builder
    public Rental(String identity, LocalDate startDate, LocalDate endDate, Long price) {
        this.identity = identity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }
}
