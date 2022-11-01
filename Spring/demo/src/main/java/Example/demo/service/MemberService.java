package Example.demo.service;

import Example.demo.domain.Member;
import Example.demo.repository.MemberRepository;
import Example.demo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    // Ctrl + Alt + m 메서드 추출기능
    private void validateDuplicateMember(Member member) {
        /*
        null일 가능성이 있는 객체를 Optional로 감싸서
        if ~ else 대신 ifPresent와 같은 메서드를 통해 진행
         */

        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
