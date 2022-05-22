package com.example.Dokkaebi.help;

import com.example.Dokkaebi.rental.JpaRentalRepo;
import com.example.Dokkaebi.member.MemberRepository;
import com.example.Dokkaebi.rental.JpaRentalRepoSupport;
import com.example.Dokkaebi.help.dto.MyPageResponse;
import com.example.Dokkaebi.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
/*
서비스 단에서는 service 를 interface 로 만든 후, 동일한 호출에 다른 로직이 사용될 때
impl 클래스를 여러가지 만들어 interface 를 사용한다. (예, 로그인 메서드는 interface,
하지만 카카오, 구글 등의 시도 로직이 다른 것은 impl.
*/
public class MyPageService {
    private final JpaRentalRepo jpaRentalRepo;
    private final JpaRentalRepoSupport jpaRentalRepoSupport;
    //일반 EntityManager 호출
    private final MemberRepository memberRepository;

//일반 repo 에서 Member 객체로 조회하는 것을 정의하여 써보기 (아래)
//    @Transactional
//    public MyPageResponse viewMyPage(Long memberId){
//        return new MyPageResponse(jpaRentalRepoSupport.findByMemberId(memberId));
//    }
    @Transactional
    public MyPageResponse viewMyPage(Long memberId){
        Member member = memberRepository.findOne(memberId);
        return new MyPageResponse(jpaRentalRepo.findByMember(member), member);
    }
}
