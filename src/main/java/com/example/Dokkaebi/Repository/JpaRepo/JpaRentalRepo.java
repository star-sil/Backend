package com.example.Dokkaebi.Repository.JpaRepo;

import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaRentalRepo extends JpaRepository<Rental,Long> {
    List<Rental> findByMember(Member member);
}
