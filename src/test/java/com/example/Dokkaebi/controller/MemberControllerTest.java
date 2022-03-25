package com.example.Dokkaebi.controller;

import com.example.Dokkaebi.Repository.MemberRepository;
import com.example.Dokkaebi.controller.dtos.MemberRequestDto;
import com.example.Dokkaebi.domain.Auth;
import com.example.Dokkaebi.domain.Member;
import com.example.Dokkaebi.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member().builder()
                .identity("sadf")
                .name("Asdf")
                .auth(Auth.ADMIN)
                .birth("asdf")
                .build();
        MemberRequestDto requestDto = new MemberRequestDto(member);

        String url = "http://localhost:" + port + "/member/new";
        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,requestDto,String.class );
        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 멤버리스트가_null일때_체크할것들() throws Exception{
        //given
        List<Member> members = new ArrayList<>();
        members.add(new Member());

        //when
        Member findMember = members.get(0);

        //then
        Assertions.assertThat(findMember).isNotNull();
    }
}