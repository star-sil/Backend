package com.example.Dokkaebi.rental;

import com.example.Dokkaebi.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaRentalRepo extends JpaRepository<Rental,Long> {
    List<Rental> findByMember(Member member);
}
