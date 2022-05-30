package com.example.Dokkaebi.member;

import com.example.Dokkaebi.rental.JpaRentalRepoSupport;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class JpaMemberRepoSupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public JpaMemberRepoSupport(JPAQueryFactory jpaQueryFactory){
        super(Member.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QMember member = QMember.member;

    public List<Member> findByIdentity(String identity){
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.identity.eq(identity))
                .fetch();
    }
}
