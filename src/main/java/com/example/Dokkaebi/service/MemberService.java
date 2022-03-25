package com.example.Dokkaebi.service;

import com.example.Dokkaebi.Repository.MemberRepository;
import com.example.Dokkaebi.Repository.TokenRepository;
import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.domain.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MemberRepo;

    @Transactional
    public Long join(Member member) {
        if (!validateDuplicateMember(member)) {
            return -1L;
        }
        MemberRepo.save(member);
        return member.getId();
    }

    @Transactional
    public Member findMember(String identity) {
        return MemberRepo.findByIdentity(identity);
    }


    private boolean validateDuplicateMember(Member member) {
        Member findMember = MemberRepo.findByIdentity(member.getIdentity());
        if (findMember.getIdentity() == null) {
            return true;
        } else {
            return false;
        }
    }

}
