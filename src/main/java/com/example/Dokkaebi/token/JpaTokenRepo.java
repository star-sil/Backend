package com.example.Dokkaebi.token;

import com.example.Dokkaebi.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaTokenRepo extends JpaRepository<Token,Long> {
     Token findByMember(Member member);
}
