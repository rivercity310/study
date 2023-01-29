package com.example.springdatajpa.demo.repository;

import com.example.springdatajpa.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUserNameAndAgeGreaterThan(String userName, int age);
}
