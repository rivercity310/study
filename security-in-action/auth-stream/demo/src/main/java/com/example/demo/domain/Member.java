package com.example.demo.domain;

import com.example.demo.domain.enums.AlgorithmType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private AlgorithmType algorithm;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    @Builder
    public Member(String username, String password, AlgorithmType algorithm, Set<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.algorithm = algorithm;
        this.authorities = authorities;
    }
}
