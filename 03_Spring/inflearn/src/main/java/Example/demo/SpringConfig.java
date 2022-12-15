package Example.demo;

import Example.demo.repository.MemberRepository;
import Example.demo.repository.MemoryMemberRepository;
import Example.demo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
java 코드로 직접 bean 등록하기
 */

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
