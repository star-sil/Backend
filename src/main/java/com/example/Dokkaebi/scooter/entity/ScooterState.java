package com.example.Dokkaebi.scooter.entity;

import com.example.Dokkaebi.rental.Rental;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class ScooterState {
    @Id @GeneratedValue
    private Long id;
    private String identity;
    private Status status;
    private int useCount;
    @OneToMany
    private List<Rental> rentals = new ArrayList<>();

    @Builder
    public ScooterState(String identity, Status status, int useCount) {
        this.identity = identity;
        this.status = status;
        this.useCount = useCount;
    }
}
