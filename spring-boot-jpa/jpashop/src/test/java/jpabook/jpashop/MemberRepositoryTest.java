package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test             /* org.junit.jupiter.api가 제공하는 Test 어노테이션 사용해야 함 */
    @Transactional    /* 테스트 캐이스에 이 어노테이션이 붙으면 테스트 종료 후 자동으로 Rollback -> 테스트 후 DB에 데이터 저장 X */
    @Rollback(false)  /* Rollback 취소 */
    public void testMember() throws Exception {
        // given
        Member member = new Member();
        member.setUsername("memberA");

        // when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        // then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());

        // == 비교 결과는 true -> 같은 트랜잭션 안에서 실행되기 때문에 영속성 컨텍스트 내 1차 캐시에서 가져옴
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}