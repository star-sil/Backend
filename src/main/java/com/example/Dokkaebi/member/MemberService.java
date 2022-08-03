package com.example.Dokkaebi.member;

import com.example.Dokkaebi.exception.ApiException;
import com.example.Dokkaebi.exception.ExceptionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository MemberRepo;
    private final JpaMemberRepo jpaMemberRepo;

    @Transactional
    public Long join(Member member) {
        if(MemberRepo.findByIdentity(member.getIdentity()).isEmpty()){
            MemberRepo.save(member);
        } else {
            throw new ApiException(ExceptionEnum.IdentityDuplicated);
        }
        return member.getId();
    }

    //새로운 객체 반환 시 문제가 발생할 수 있음.
    public Member findMember(String identity) {
        return MemberRepo.findByIdentity(identity)
                .orElseThrow(()->new ApiException(ExceptionEnum.IdentityNotMatched));
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

    @Override
    // UserDetailsService 를 상속하여 해당 메소드를 구현해야지만 사용가능하다.
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException {
        return jpaMemberRepo.findByIdentity(identity);
    }
}
