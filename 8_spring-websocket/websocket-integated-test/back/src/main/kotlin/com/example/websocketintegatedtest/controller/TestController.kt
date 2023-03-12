package com.example.websocketintegatedtest.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class TestController {
    @MessageMapping("/good")
    fun handle(message: String): String {
        return "$message - good"
    }
}