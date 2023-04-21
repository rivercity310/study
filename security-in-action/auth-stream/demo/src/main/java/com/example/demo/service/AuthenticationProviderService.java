package com.example.demo.service;

import com.example.demo.model.CustomMemberDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationProviderService implements AuthenticationProvider {
    private final JpaUserDetailsService jpaUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println("username = " + username);
        System.out.println("password = " + password);

        CustomMemberDetails member = jpaUserDetailsService.loadUserByUsername(username);

        return switch (member.member().getAlgorithm()) {
            case BCRYPT -> checkPassword(member, password, new BCryptPasswordEncoder());
            case SCRYPT -> checkPassword(member, password, SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
        };
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomMemberDetails member, String rawPassword, PasswordEncoder encoder) {
        if (encoder.matches(rawPassword, member.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    member.getUsername(),
                    member.getPassword(),
                    member.getAuthorities()
            );
        }

        throw new BadCredentialsException("Bad Credentials");
    }
}
