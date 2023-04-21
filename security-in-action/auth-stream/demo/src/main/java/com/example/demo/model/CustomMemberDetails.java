package com.example.demo.model;

import com.example.demo.domain.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public record CustomMemberDetails(Member member) implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return member.getAuthorities()
                .stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
