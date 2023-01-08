package io.security.basicSecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 인증 API - Form Login 인증
 http.formLogin()
 .loginPage("/login.html")               // 사용자 정의 로그인 페이지
 .defaultSuccessUrl("/home")             // 로그인 성공 후 이동 페이지
 .failureUrl("/login.html?error=true")   // 로그인 실패 후 이동 페이지
 .usernameParameter("username")          // 아이디 파라미터명 설정
 .passwordParameter("password")          // 패스워드 파라미터명 설정
 .loginProcessingUrl("/login")           // 로그인 Form Action Url
 .successHandler(loginSuccessHandler())  // 로그인 성공 후 핸들러
 .failureHandler(loginFailureHandler())  // 로그인 실패 후 핸들러
 */


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인가 방식
        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        // 인증 방식
        http.formLogin()
                .loginPage("/loginPage")
                .defaultSuccessUrl("/")
                .failureUrl("/loginPage")
                .usernameParameter("userId")
                .passwordParameter("passwd")
                .loginProcessingUrl("/login_prop")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        System.out.println("authentication " + authentication.getName());
                        response.sendRedirect("/");
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        System.out.println("exception " + exception.getMessage());
                        response.sendRedirect("/loginPage");
                    }
                })
                .permitAll();

    }
}
