package com.example.springdatajpa.demo.repository;


import com.example.springdatajpa.demo.dto.MemberDTO;
import com.example.springdatajpa.demo.entity.Member;
import com.example.springdatajpa.demo.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Autowired TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

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

        Member findMember1 = memberRepository.findById(member1.getId()).orElseGet(() -> fail("Member is NULL"));
        Member findMember2 = memberRepository.findById(member2.getId()).orElseGet(() -> fail("Member is NULL"));

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
        Member member2 = memberRepository.save(new Member("BBB", 20));
        List<Member> findMember = memberRepository.findByUserName("BBB");
        assertThat(findMember.get(0)).isEqualTo(member2);
    }

    @Test
    public void findUsernameList() {
        memberRepository.save(new Member("AAA", 10));
        memberRepository.save(new Member("BBB", 20));

        List<String> userNameList = memberRepository.findUserNameList();
        for (String s : userNameList) System.out.println("s = " + s);
    }

    @Test
    public void findMemberDTOTest() {
        Team team = new Team("TeamA");
        // teamRepository.save(team);           // cascade all

        Member member = new Member("AAA", 10, team);
        memberRepository.save(member);

        List<MemberDTO> findMemberDTO = memberRepository.findMemberDTO();

        for (MemberDTO memberDTO : findMemberDTO) {
            System.out.println("memberDTO = " + memberDTO);
        }
    }

    @Test
    public void collectionTest() {
        Member member1 = new Member("AAA");
        Member member2 = new Member("AAAAA");
        Member member3 = new Member("AAAAAA");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5);
        List<String> userNameListByCollection = memberRepository.findUserNameListByCollection(lst);

        for (String s : userNameListByCollection) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void returnTypeTest() {
        memberRepository.save(new Member("AAA", 11));
        memberRepository.save(new Member("BBB", 22));

        Member findMember = memberRepository.findByAge(11);
        List<Member> findListMember = memberRepository.findListByAge(11);
        Optional<Member> findOptionalMember = memberRepository.findOptionalByAge(22);

        Member mem = findOptionalMember.orElseGet(() -> fail("Member is NULL"));

        assertThat(findMember.getAge()).isEqualTo(11);
        assertThat(findListMember.size()).isEqualTo(1);
        assertThat(findListMember.get(0).getAge()).isEqualTo(11);
        assertThat(mem.getAge()).isEqualTo(22);
    }

    @Test
    public void paging() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "userName"));
        Page<Member> page = memberRepository.findPageByAge(age, pageRequest);

        List<Member> content = page.getContent();
        long totalCount = page.getTotalElements();

        for (Member member : content) System.out.println("member = " + member);
        System.out.println("totalCount = " + totalCount);

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0);      // 페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2);          // 전체 페이지 개수
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    public void slicing() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "userName"));
        Slice<Member> slice = memberRepository.findSliceByAge(age, pageRequest);
        Slice<MemberDTO> toMap = slice.map(m -> new MemberDTO(m.getId(), m.getUserName(), null));
        List<Member> content = slice.getContent();

        assertThat(content.size()).isEqualTo(3);
        assertThat(slice.getNumber()).isEqualTo(0);
        assertThat(slice.isFirst()).isTrue();
        assertThat(slice.hasNext()).isTrue();
    }

    @Test
    public void bulkUpdate() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 20));
        memberRepository.save(new Member("member3", 21));
        memberRepository.save(new Member("member4", 31));
        memberRepository.save(new Member("member5", 43));

        Integer resultCount = memberRepository.bulkAgePlus(20);

        // 벌크 연산 이후에는 영속성 컨텍스트를 날려야함 -> 불일치 문제
        // em.flush();        --> Modifying 어노테이션 옵션으로 지정 가능 (clearAutomatically = true)
        // em.clear();

        List<Member> result = memberRepository.findByUserName("member5");
        Member member5 = result.get(0);

        System.out.println("member5 = " + member5);

        assertThat(resultCount).isEqualTo(4);
    }

    @Test
    public void findMemberLazy() {
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findEntityGraphByUserName("member1");

        for (Member member : members) {
            System.out.println("member.getUserName() = " + member.getUserName());
            System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());
            System.out.println("member.getTeam().getTeamName() = " + member.getTeam().getTeamName());
        }
    }

    @Test
    public void queryHint() {
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        Member findMember = memberRepository.findReadOnlyByUserName(member1.getUserName()).orElseGet(() -> fail("Not Found"));
        findMember.changeName("member2");       // readOnly = true 이므로 변경 무시됨
    }

    @Test
    public void lock() {
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        List<Member> member = memberRepository.findLockByUserName("member1");
    }
}