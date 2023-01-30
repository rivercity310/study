package com.example.springdatajpa.demo.repository;

import com.example.springdatajpa.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUserNameAndAgeGreaterThan(String userName, int age);

    // @Query(name = "Member.findByUserName")   --> 관례상 생략 가능
    List<Member> findByUserName(@Param("userName") String userName);

    @Query("select m from Member m where m.userName = :userName and m.age = :age")      // --> 실무 사용: 엔티티가 아닌 여기에 바로 적용 가능
    List<Member> findUser(@Param("userName") String userName, @Param("age") int age);
}
