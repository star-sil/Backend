package com.example.Dokkaebi.rental;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//repo support 는 JpaRepository 가 하지 못하는 복잡한 쿼리를 구현하기 위해 만드는 것.
//queryDSL 은 쿼리 실행 단계에서 오류를 알 수 있는 기존 방식에서 컴파일 단계에서 오류를 알 수 있기에 좋음
// ++가독성도 좋다 (JPQL, SQL 은 type-check 가 불가능 하다. 우리도 enum 쓰니까..)
public class JpaRentalRepoSupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    //상속 받았기에 생성자를 만들어 줌, configuration 에서 등록한 jpaQF를 알아서 주입.
    public JpaRentalRepoSupport(JPAQueryFactory jpaQueryFactory){
        super(Rental.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    //Q-type 클래스는 QueryDSL을 사용하여 메소드 기반으로 쿼리를 작성할 때 우리가 만든
    //도메인 클래스의 구조를 설명해주는 메타데이터 역할을 하며 쿼리의 조건을 설정할 때 사용된다.
    //음... 사실 그냥 static 으로 된 클래스 있으면 객체를 만들 필요가 없으니 참고 하는거다.
    QRental qRental = QRental.rental;

    //사실, 아래 findByMemberId 도 일반 RentalRepo 에서 사용자를 지정하면 그냥 사용할 수 있음.
    public List<Rental> findByMemberId(Long memberId){
        return jpaQueryFactory
                .selectFrom(qRental)
                .where(qRental.member.id.eq(memberId))
                .fetch();
    }
}
