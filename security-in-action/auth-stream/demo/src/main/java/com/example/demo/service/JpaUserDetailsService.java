package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.model.CustomMemberDetails;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomMemberDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println("member.getPassword() = " + member.getPassword());
        
        return new CustomMemberDetails(member);
    }
}
