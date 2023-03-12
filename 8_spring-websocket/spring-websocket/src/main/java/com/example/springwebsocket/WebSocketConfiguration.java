package com.example.springwebsocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * - @EnableWebSocket: 웹소켓 서버를 사용하도록 설정
 * - addHandler(): 웹소켓 서버의 엔드포인트(url:port/room) 설정, 핸들러 등록
 * - setAllowedOrigins("*"): 웹소켓 서버에 요청 시 모든 요청 수용 (CORS: Schema, Host, Port 동일 조건)
 */

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(signalingSocketHandler(), "/room")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler signalingSocketHandler() {
        return new WebSocketHandler();
    }
}
