package io.security.basicSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

/** 인증 API - Logout
 http.logout()                                  // 로그아웃 처리
 .logoutUrl("/logout")                          // 로그아웃 처리 URL
 .logoutSuccessUrl("/login")                    // 로그아웃 성공 후 이동페이지
 .deleteCookies("JSESSIONID", "remember-me")    // 로그아웃 후 쿠키 삭제
 .addLogoutHandler(logoutHandler())             // 로그아웃 핸들러
 .logoutSuccessHandler(logoutSuccessHandler())  // 로그아웃 성공 후 핸들러
 */

/** 인증 API - Remember Me
 http.rememberMe()
 .rememberMeParameter("remember")        // 기본 파라미터명 remember-me
 .tokenValiditySeconds(3600)             // Default 14일
 .alwaysRemember(true)                   // 리멤버 미 기능이 활성화되지 않아도 항상 실행
 .userDetailsService(userDetailsService) // 사용자 계정 조회할 때 필요 (필수)
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 인가 방식
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated();


        // 인증 방식
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
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


        // 로그아웃 & 리멤버 미
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                        HttpSession session = request.getSession();
                        session.invalidate();
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.sendRedirect("/login");
                    }
                })
                .deleteCookies("remember-me")

        .and()

                .rememberMe()
                .rememberMeParameter("remember")
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService);


    }
}
