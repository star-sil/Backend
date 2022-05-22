package com.example.Dokkaebi.member;

import org.springframework.data.jpa.repository.JpaRepository;

//기본적인 CRUD 를 상속으로 제공해줌.
//EntityManager 대신 Repo 를 상속받음으로써 CRUD 에 대한 작성이 필요 없어짐.
public interface JpaMemberRepo extends JpaRepository<Member,Long> {
    /*
    @Query(value = "select name, age from sample_member where name = :name", nativeQuery=true)
    List<CrudEntity> searchParamRepo(@Param("name") String name);
    위와 같은 방법으로 Query를 직접 쓸 수도 있지만, 어느정도 한계가 있음..
    */
    Member findByIdentity(String identity);
}
