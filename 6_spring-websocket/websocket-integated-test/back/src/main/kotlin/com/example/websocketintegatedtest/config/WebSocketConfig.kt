package com.example.websocketintegatedtest.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.*

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            .addEndpoint("/test")
            .setAllowedOrigins("http://localhost:63343")
            .withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry
            .setApplicationDestinationPrefixes("/simple")
            .enableSimpleBroker("/topic", "/queue")
    }
}