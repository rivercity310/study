package com.example.thingsFlow.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity // 시큐리티 필터가 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // csrf토큰 비활성화(테스트시 걸어두는게 좋음) 시큐리티는 csrf토큰이 있어야 접근가능함
                .authorizeRequests() // 인가 요청이 오면
                .antMatchers("/**",
                        "/api/*",
                        "/swagger-ui/*",
                        "/v1/*",
                        "/v2/*",
                        "/v3/*") // 해당 경로들은
                .permitAll() // 접근을 허용한다.
                .anyRequest() // 다른 모든 요청은
                .authenticated(); // 인증이 되야 들어갈 수 있다.
    }
}
