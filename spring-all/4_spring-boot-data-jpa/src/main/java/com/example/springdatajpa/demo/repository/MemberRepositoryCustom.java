package com.example.springdatajpa.demo.repository;

import com.example.springdatajpa.demo.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
