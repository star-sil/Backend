package com.example.Dokkaebi.service;

import com.example.Dokkaebi.Repository.RentalRepository;
import com.example.Dokkaebi.domain.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepo;

    @Transactional
    public void joinRental(Rental rental) {
        rentalRepo.save(rental);
    }
}
