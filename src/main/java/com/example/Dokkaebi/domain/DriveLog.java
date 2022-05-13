package com.example.Dokkaebi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class DriveLog {
    @Id @GeneratedValue
    private Long id;
    private int cycle;

    @OneToMany(mappedBy = "driveLog")
    private List<Scooter> scooters = new ArrayList<>();

    @ManyToOne @JoinColumn(name = "rental")
    private Rental rental;

    @Builder
    public DriveLog(int cycle, Scooter scooter, Rental rental) {
        this.cycle = cycle;
        this.scooters.add(scooter);
        this.rental = rental;
    }
}
