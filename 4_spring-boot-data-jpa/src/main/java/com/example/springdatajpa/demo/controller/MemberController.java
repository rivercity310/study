package com.example.springdatajpa.demo.controller;

import com.example.springdatajpa.demo.dto.MemberDTO;
import com.example.springdatajpa.demo.entity.Member;
import com.example.springdatajpa.demo.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) memberRepository.save(new Member("USER" + i, i));
    }

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not Found Member"));
        return member.getUserName();
    }

    @GetMapping("/members2/{id}")
    public  findMember2(@PathVariable("id") Member member) {    /* 도메인 클래스 컨버터 */
        return member.getUserName();
    }

    @GetMapping("/members")
    public Page<MemberDTO> list(@PageableDefault(size = 5, sort = "id") Pageable pageable) {     /* 특정 영역 paging 파라미터 설정 어노테이션 */
        // http://localhost:8080/members?page=0&size=3&sort=id,desc&sort=userName,asc  -> asc default이므로 생략 가능
        // 자동으로 pageRequest 객체 생성 & 주입
        return memberRepository.findAll(pageable).map(MemberDTO::new);
    }
}
