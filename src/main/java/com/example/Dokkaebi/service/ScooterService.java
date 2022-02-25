package com.example.Dokkaebi.service;

import com.example.Dokkaebi.Repository.ScooterRepository;
import com.example.Dokkaebi.domain.Scooter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScooterService {
    private final ScooterRepository ScooterRepo;

    public List<Scooter> findScooter(Scooter scooter) {
        return ScooterRepo.findByBike(scooter.getBike());
    }

    public List<Scooter> findAllScooter() {
        List<String> bikes = new ArrayList<>();
        bikes.add("0001"); bikes.add("0002"); bikes.add("0003"); bikes.add("0004");
        bikes.add("0005"); bikes.add("0006");
        return ScooterRepo.findAllByBike(bikes);
    }

}
