package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) /* spring이 제공하는 Transactional 어노테이션 사용 권장 */
@RequiredArgsConstructor    /* final 필드만 가지고 생성자를 만들어줌 */
public class MemberService {
    private final MemberRepository memberRepository;

    /* 회원 가입 */
    @Transactional(readOnly = false)  /* 쓰기에는 readOnly false (default) */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /* 회원 전체 조회 */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /* 회원 한건 조회 */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    private void validateDuplicateMember(Member member) {
        // Exception
        List<Member> findMembers =
                memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원");
    }
}
