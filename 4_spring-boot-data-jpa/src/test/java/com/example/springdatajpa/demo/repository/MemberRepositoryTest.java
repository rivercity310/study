package com.example.springdatajpa.demo.repository;


import com.example.springdatajpa.demo.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember() {
        System.out.println("memberRepository = " + memberRepository.getClass());

        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void crud() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        findMember1.changeName("changed name");

        // 단건 조회 검증
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
        assertThat(findMember1.getUserName()).isEqualTo("changed name");
        assertThat(member1.getUserName()).isEqualTo("changed name");

        // 리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        Long count = memberRepository.count();
        assertThat(all.size()).isEqualTo(count.intValue());

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
        Long count1 = memberRepository.count();
        assertThat(count1).isEqualTo(0);
    }

    @Test
    public void findByUserNameAndAgeGreaterThan() {
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("AAA", 20));

        List<Member> result = memberRepository.findByUserNameAndAgeGreaterThan("AAA", 15);

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.get(0).getUserName()).isEqualTo("AAA");
    }

    @Test
    public void namedQuery() {
        Member member1 = memberRepository.save(new Member("AAA", 10));
        Member member2 = memberRepository.save(new Member("BBB", 20));

        List<Member> findMember = memberRepository.findByUserName("BBB");

        assertThat(findMember.get(0)).isEqualTo(member2);
    }

    @Test
    public void namedQueryOnInterface() {
        Member member1 = memberRepository.save(new Member("AAA", 10));
        Member member2 = memberRepository.save(new Member("BBB", 20));

        List<Member> findMember = memberRepository.findByUserName("BBB");

        assertThat(findMember.get(0)).isEqualTo(member2);
    }
}