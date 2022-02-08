package com.example.Dokkaebi.service;

import com.example.Dokkaebi.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@RequiredArgsConstructor
class MemberServiceTest {
    private final MemberRepository memberRepository;


}