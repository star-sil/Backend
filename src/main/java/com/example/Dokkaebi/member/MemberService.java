package com.example.Dokkaebi.member;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository MemberRepo;
    private final JpaMemberRepo jpaMemberRepo;

    @Transactional
    public Long join(Member member) {
        if (!validateDuplicateMember(member)) {
            return -1L;
        }
        MemberRepo.save(member);
        return member.getId();
    }

    //새로운 객체 반환 시 문제가 발생할 수 있음.
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

    public void updateMember(MemberRequestDto memberRequestDto){
        Member member = jpaMemberRepo
                .findByIdentity(memberRequestDto.getIdentity());
        member.updateFromRequest(memberRequestDto);
        jpaMemberRepo.save(member);
    }

    @Transactional(readOnly = true)
    public Member findMember2(String identity){
        return jpaMemberRepo.findByIdentity(identity);
    }
}
