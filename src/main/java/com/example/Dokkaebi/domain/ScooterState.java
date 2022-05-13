package com.example.Dokkaebi.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class ScooterState {
    @Id @GeneratedValue
    private Long id;
    private String identity;
    private Status status;
    private int cycle;
    @OneToMany
    private List<Rental> rentals = new ArrayList<>();



}
