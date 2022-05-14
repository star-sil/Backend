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
    private int useCount;

    @OneToMany(mappedBy = "driveLog")
    private List<Scooter> scooters = new ArrayList<>();

    @ManyToOne @JoinColumn(name = "rental_id")
    private Rental rental;

    @Builder
    public DriveLog(int useCount, Scooter scooter, Rental rental) {
        this.useCount = useCount;
        this.scooters.add(scooter);
        this.rental = rental;
    }
}
