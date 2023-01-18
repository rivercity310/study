package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional   /* Test에서는 종료 후 자동 롤백 기능 추가 */
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    // @Rollback(value = false)  /* 롤백 안하고 commit 하도록 설정 */
    public void 회원가입() throws Exception {
        // given
        Member member = new Member();
        member.setName("승수");

        // when
        Long savedId = memberService.join(member);

        // then: 한 트랜잭션 내에 엔티티는 동일성이 보장 (== 비교 -> true)
        // 영속성 컨텍스트 내 1차 캐시에서 바로 반환됨 (동일 트랜잭션)
        em.flush();     // insert 쿼리를 보고 롤백하고 싶으면 flush
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복확인() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        // when
        memberService.join(member1);

        try { memberService.join(member2); }    /* 예외 발생 지점 */
        catch (IllegalStateException e) { return; }

        // then
        fail("예외가 발생해야 한다");
    }
}